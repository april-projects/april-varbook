<script setup>
import {ref} from 'vue'

const isOpen = ref(false)
const payType = ref('alipay')

const qrMap = {
  alipay: {
    label: '支付宝',
    img: 'https://tencent.cos.mobaijun.com/img/icon/alipay.png',
  },
  wechat: {
    label: '微信',
    img: 'https://tencent.cos.mobaijun.com/img/icon/wechat.png',
  },
}
</script>
<template>
  <el-link class="donate-link" type="primary" @click="isOpen = true">
    <img alt="赏" class="button-icon shang-icon" src="@/assets/images/shang.svg"/>
  </el-link>
  <el-dialog v-if="isOpen" v-model="isOpen" :show-close="true" :title="null" class="donate-dialog" width="380px">
    <div class="pay-tabs">
      <button
          v-for="(item, key) in qrMap"
          :key="key"
          :class="['pay-tab', { active: payType === key }]"
          @click="payType = key"
      >
        {{ item.label }}
      </button>
    </div>
    <div class="qr-wrap">
      <img :alt="qrMap[payType].label" :src="qrMap[payType].img" draggable="false"/>
    </div>
  </el-dialog>
</template>

<style scoped>
.donate-link {
  text-decoration: none !important;
}

.qr-wrap {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 8px;
}

img {
  width: 320px;
  max-width: 100%;
  display: block;
  margin: 0 auto;
}

.button-icon.shang-icon {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background: none;
  padding: 0;
  margin: 0;
}

.pay-tabs {
  display: flex;
  justify-content: center;
  margin-bottom: 10px;
}

.pay-tab {
  border: none;
  outline: none;
  background: #f5f5f5;
  color: #606266;
  font-size: 15px;
  padding: 6px 18px;
  margin: 0 2px;
  border-radius: 4px 4px 0 0;
  cursor: pointer;
  transition: background 0.2s, color 0.2s;
}

.pay-tab.active {
  background: #409eff;
  color: #fff;
}
</style>
