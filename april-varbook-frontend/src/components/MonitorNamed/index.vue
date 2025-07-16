<!--suppress HtmlDeprecatedAttribute -->

<script setup>
import {ElNotification} from 'element-plus'
import {computed, ref, watch} from 'vue'

import useSearch from '@/store/search'
import {parseNamedVariable, STYLE_LANG} from '@/utils/character/standard'
import {copy} from '@/utils/window/location'

const search = useSearch()
const namedVariables = computed(() => search.namedVariables)

const tableData = ref(parseNamedVariable(namedVariables.value))
const isLoading = computed(() => search.isLoading)

const sendNotification = (title, message, type) =>
    ElNotification({
      title,
      message,
      type,
      dangerouslyUseHTMLString: true,
      offset: 60
    })

watch(namedVariables, (newVal) => {
  const autoStyleKey = copy.auto(newVal)
  if (autoStyleKey) {
    sendNotification(
        '自动复制',
        `变量命名法: <b>${STYLE_LANG[autoStyleKey]}</b>`,
        'success'
    )
  }
  tableData.value = parseNamedVariable(newVal)
  search.setIsLoading(false)
})

const copyData = (style, key, value) => {
  if (copy.text(key, value))
    sendNotification('复制成功', `变量命名法: <b>${style}</b>`, 'success')
  else sendNotification('复制失败', '请手动复制，您的浏览器不支持', 'warning')
}

const commonNames = computed(() => namedVariables.value.commonNames || [])
const lastCopied = ref('')
// 变量名样式映射
const styleColorMap = {
  smallHump: '#4caf50', // 小驼峰 绿色
  bigHump: '#2196f3',   // 大驼峰 蓝色
  smallSnake: '#ff9800', // 小蛇形 橙色
  bigSnake: '#e91e63',   // 大蛇形 粉色
  spinal: '#9c27b0',     // 脊柱 紫色
  description: '#607d8b', // 注释 灰蓝
  default: '#90a4ae'     // 其它 灰色
}

// 取变量名样式色彩
function getNameColor(name) {
  if (!name) return styleColorMap.default
  if (/^[a-z][a-zA-Z0-9]*$/.test(name)) return styleColorMap.smallHump
  if (/^[A-Z][a-zA-Z0-9]*$/.test(name)) return styleColorMap.bigHump
  if (/^[a-z0-9]+(_[a-z0-9]+)+$/.test(name)) return styleColorMap.smallSnake
  if (/^[A-Z0-9]+(_[A-Z0-9]+)+$/.test(name)) return styleColorMap.bigSnake
  if (/^[a-z0-9]+(-[a-z0-9]+)+$/.test(name)) return styleColorMap.spinal
  return styleColorMap.default
}

const copyCommonName = (name) => {
  if (copy.text('常用命名', name)) {
    lastCopied.value = name
    sendNotification('复制成功', `常用命名: <b>${name}</b>`, 'success')
  } else {
    sendNotification('复制失败', '请手动复制，您的浏览器不支持', 'warning')
  }
}
</script>

<template>
  <div v-show="namedVariables" class="named-table">
    <el-table
        v-loading="isLoading"
        :data="tableData.filter(row => row.key !== 'commonNames')"
        :highlight-current-row="false"
    >
      <el-table-column
          align="center"
          label="变量命名法"
          prop="style"
          width="120"
      />
      <el-table-column align="center" label="变量名" prop="data">
        <template #default="scope">
          <el-link
              type="primary"
              @click="copyData(scope.row.style, scope.row.key, scope.row.data)"
          >{{ scope.row.data }}
          </el-link>
        </template>
      </el-table-column>
    </el-table>
  </div>
  <!-- 常用命名标签区 -->
  <div v-if="commonNames.length" class="common-names-bottom">
    <el-tag
        v-for="(name, idx) in commonNames"
        :key="name"
        :type="lastCopied === name ? 'primary' : 'info'"
        class="common-name-tag"
        effect="plain"
        size="small"
        @click="copyCommonName(name)"
        style="cursor:pointer;user-select:all;"
    >
      {{ name }}<span v-if="lastCopied === name" style="margin-left: 2px;">✔</span>
    </el-tag>
  </div>
</template>

<style scoped>
.common-names-bottom {
  width: 100%;
  max-width: 900px;
  margin: 20px auto 0 auto;
  display: flex;
  flex-wrap: wrap;
  gap: 8px 14px;
  justify-content: center;
  align-items: center;
  min-height: 24px;
  padding: 0 0 6px 0;
  box-sizing: border-box;
}

.common-name-tag {
  font-size: 13px;
  border-radius: 12px !important;
  padding: 2px 16px !important;
  transition: box-shadow 0.18s, transform 0.18s;
  box-shadow: 0 1px 6px 0 rgba(33, 150, 243, 0.07);
  background: #232f3e;
  border: none !important;
  color: #2196f3 !important;
}

.common-name-tag:hover {
  background: #2196f3 !important;
  color: #fff !important;
  transform: translateY(-3px) scale(1.07);
  box-shadow: 0 4px 16px 0 rgba(33, 150, 243, 0.18);
  z-index: 2;
}
</style>
