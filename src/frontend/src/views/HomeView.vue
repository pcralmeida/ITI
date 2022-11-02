<template>
    <div class="home">
    </div>  
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';

@Component
export default class HomeView extends Vue {
  appName: string = process.env.VUE_APP_NAME;
}
</script>

<style scoped>
  .home {
    background-color: #009FE3;
    background-size: cover;
    height: 100vh;
    width: 100vw;
  }
</style>
