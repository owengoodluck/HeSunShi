<template>
    <div id="viewer" class="viewer"></div>
</template>

<script>
    import PhotoSphereViewer from 'photo-sphere-viewer';
    import 'photo-sphere-viewer/dist/photo-sphere-viewer.css';
    export default {
        data() {
            return {
                panorama: require('../../assets/2.jpg')
            }
        },
        mounted() {
            var container = new PhotoSphereViewer({
                container: document.getElementById('viewer'),
                panorama: this.panorama,
                size:{
                    width:'100%',
                    height:'100%'
                },
                navbar: false
            });
        },
        methods: {

        }
    }
</script>

<style scoped>
    .viewer {
        position: fixed;
        top: 0;
    }
</style>
