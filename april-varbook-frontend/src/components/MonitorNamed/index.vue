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
  max-width: 1200px;
  margin: 20px auto 0 auto;
  display: flex;
  flex-wrap: wrap;
  gap: 8px 14px;
  justify-content: center;
  align-items: center;
  min-height: 2px;
  padding: 0 0 2px 0;
  box-sizing: border-box;
}

.common-name-tag {
  font-size: 13px;
  border-radius: 12px !important;
  padding: 6px 6px !important;
  transition: box-shadow 0.18s, transform 0.18s, background 0.18s, color 0.18s;
  box-shadow: 0 1px 6px 0 rgba(33, 150, 243, 0.07);
  /* 颜色变量由 theme.js 动态注入 */
  background: var(--varBook-tag-bg) !important;
  color: #2196f3 !important;
  border: none !important;
  cursor: pointer;
  user-select: all;
}

.common-name-tag:hover {
  background: var(--varBook-tag-hover-bg) !important;
  transform: translateY(-3px) scale(1.07);
  box-shadow: 0 4px 16px 0 rgba(33, 150, 243, 0.18);
  z-index: 2;
}
</style>
