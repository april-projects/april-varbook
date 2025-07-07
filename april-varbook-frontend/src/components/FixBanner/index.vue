<script setup>
import bananaBase64 from '@/utils/data/bannerBase64'
</script>

<template>
  <a
      href="https://www.mobaijun.com"
      style="display: contents; cursor: auto"
      target="_self"
  ><img :src="bananaBase64" alt="" class="banner"
        ondragstart="return false;"/></a>
</template>
