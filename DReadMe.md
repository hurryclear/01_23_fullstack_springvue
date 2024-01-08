> This is the all and very detailed note during development of this project.
But this is ONLY for personal usage, please don't forward without permission!

# Enviroment set up
- Frontend: `vue create frontend`
  - Element: `npm i element-ui -S`
    - import Element in main.js with the following commands:
      - `import router from './router'`
      - `import ElementUI from 'element-ui';`
      - `Vue.use(ElementUI, "small");`
- Backend: 

- 

# Frontend

## Change Layout

- get code from Element->Layout Container and put that in HomeView.vue 

- App.vue
  ```vue
  <template>
    <div id="app">
      <router-view/>
    </div>
  </template>
  <style>
  #app{
    height: 100%;
  }
  </style>
  ```

- create global.css in ./src/assets
  ```css
  html, body, div {
      margin: 0;
      padding: 0;
  }
  
  html, body {
      height: 100%;
  }
  ```

- import global.css in main.js
  ```js
  import Vue from 'vue'
  import App from './App.vue'
  import router from './router'
  import ElementUI from 'element-ui';  
  import 'element-ui/lib/theme-chalk/index.css';
  import './assets/global.css'
  
  Vue.config.productionTip = false
  Vue.use(ElementUI, "small");
  
  new Vue({
    router,
    render: h => h(App)
  }).$mount('#app')
  
  ```

  

- menu-folder (如何实现侧边栏收缩功能)
