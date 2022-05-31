<template>
  <div id="app" class="fixed-bg bg-1">
    <div id="nav">
      <!-- <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">Login</router-link>
      <router-link v-bind:to="{ name: 'account-details', params: {id: Number($store.state.user.id)}}" v-if="$store.state.token != ''">Account</router-link> -->
    </div>
    <side-bar/>
    <router-view id="main-view" />
  </div>
</template>

<script>
import SideBar from './components/SideBar.vue';

export default{
  components:{
    SideBar
  }
}


    SideBar</script>


<style>

#nav{
  
  background-color: #232323;
  margin-left: 140px;
  position: sticky;
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
  height: 20px;
  margin-top:0;
  text-align: center;
  

}


#main-view {
 
   margin-left: 140px;
   padding-left: 1rem;
}

div {
  background-color: #232323;
}

body, html {
  height: 100%;
  width: 100%;
  min-height: 100vh;
  margin: 0px;
  padding: 0px;
  color: #F2C629;
  background-color: #232323;
}

a, a:visited {
 background-color: #232323;  
 color: #DC8400; 
}

</style>


