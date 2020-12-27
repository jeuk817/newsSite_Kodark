<template>
   <div class="sidebar">
        <ul class="sidebar__menus">
            <a href="#">About</a>
            <a href="#">Help</a>
            <a href="#">Contact</a>
            <a href="#">Git</a>
        </ul>
    </div>
</template>

<script>
export default {

}
</script>

<style scoped>
      .sidebar{
            padding-top: 100px;
            width: 320px;
            /* background-color: #f7f7f5; */
            position: fixed;
            height: 100%;
        }
        .sidebar__menus{
            height: 400px;
            width: 200px;
            background-color: #f7f7f5;
            padding: 16px 0 16px 32px
        }
        .sidebar a{
            display: flex;
            color: #4a4a4a;
            text-decoration: none;
            padding: 10px;
            font-weight: 600;
            margin-bottom: 5px;
        }
</style>