<template>

    <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
        <div class="container justify-content-between">
            <DropdownMenu/>
            <div class="navbar-brand fs-4">JRM Stock System Project</div>
            <InvisibleBtn/>
        </div>
    </nav>

</template>

<script>
import DropdownMenu from "./DropdownMenu.vue";
import InvisibleBtn from "./InvisibleBtn.vue";

export default {
    components: {
        DropdownMenu, InvisibleBtn
    }
}
</script>

<styles scoped>

</styles>