<script setup>
import ButtonGithub from '@/components/ButtonGithub/index.vue'
import ButtonTheme from '@/components/ButtonTheme/index.vue'
import EnterSearch from '@/components/EnterSearch/index.vue'
import FixBanner from '@/components/FixBanner/index.vue'
import FixTotal from '@/components/FixTotal/index.vue'
import MonitorNamed from '@/components/MonitorNamed/index.vue'
import ButtonSponsorship from "@/components/ButtonGithub/ButtonSponsorship.vue";
</script>

<template>
  <div class="wrapper">
    <div class="tips">
      <button-theme/>
      <button-github/>
      <button-sponsorship/>
    </div>
    <fix-banner/>
    <enter-search/>
    <monitor-named/>
  </div>
  <fix-total/>
</template>

<style>
@import '@/assets/css/style.css';
</style>
