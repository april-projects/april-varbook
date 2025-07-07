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
</script>

<template>
  <div v-show="namedVariables" class="named-table">
    <el-table
        v-loading="isLoading"
        :data="tableData"
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
          </el-link
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
