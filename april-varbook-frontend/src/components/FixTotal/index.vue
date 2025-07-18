<script setup xmlns="http://www.w3.org/1999/html">
import {onMounted, ref} from 'vue'
import {total} from '@/api/translate'
import formatNumber from '@/utils/character/formatNumber'

const totalCharAct = ref(0)

onMounted(async () => {
  try {
    const res = await total()
    totalCharAct.value = res
  } catch (e) {
    totalCharAct.value = 0
  }
})
</script>

<template>
  <div class="total-translate">
    <div class="tips-total">
      💡 累计优化 {{ formatNumber(totalCharAct) }} 个变量字符，助力开发更丝滑
    </div>
    <div class="tips-bottom">
      <el-link class="translate-powered" href="https://www.mobaijun.com" target="_blank">
        © {{ new Date().getFullYear() }} mobaijun · 代码改变世界
      </el-link>
      <span class="tips-bottom">
        ｜本站总访问量 <span id="busuanzi_site_pv"></span> 次 ｜
        本站访客数 <span id="busuanzi_site_uv"></span> 人
      </span>
    </div>
  </div>
</template>

<style scoped>
.total-translate {
  position: fixed;
  bottom: 0;
  z-index: 999;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  padding: 10px 0;
  background-color: var(--varBook-background-color);
}

.tips-total {
  font-size: 13px;
  color: #606266;
  margin-bottom: 2px;
}

.tips-bottom {
  display: flex;
  align-items: center;
  font-size: 14px;
  color: #606266;
}

.tips-by {
  margin-right: 8px;
  font-size: 14px;
  font-weight: normal;
}

.busuanzi {
  font-size: 13px;
  color: #909399;
}
</style>