<template>
    <div>
        <div class="container" ref="container"></div>
        <div class="blocker" ref="blocker"></div>
        <div class="exterior">外观</div>
        <div class="sellingPoint">卖点</div>
        <div class="parameter">参数</div>
    </div>
</template>

<script>
    import * as THREE from 'three';
    import {CSS3DRenderer,CSS3DObject} from 'three-css3drenderer';
    import TrackballControls from 'three-trackballcontrols';
    export default {
        data() {
            return {
                length: 300,
                width: 300,
                height: 300,
                camera: null,
                scene: null,
                renderer: null,
                controls: null
            }
        },
        mounted() {
            this.init();
            this.animate();
        },
        methods: {
            init() {

                var container = this.$refs.container;
                

                this.camera = new THREE.PerspectiveCamera( 75, window.innerWidth / window.innerHeight, 1, 5000 );
                this.camera.position.set( 300, 100, 750 );

                this.scene = new THREE.Scene();

                this.renderer = new CSS3DRenderer();
                this.renderer.setSize( window.innerWidth, window.innerHeight );
                container.appendChild( this.renderer.domElement );

                var group = new THREE.Group();

                group.add( this.addElement( 'https://bzmall.oss-cn-shanghai.aliyuncs.com/front.jpg', this.length, this.height, 0, 0, this.length/2, 0 , 0, 0) );//front
                group.add( this.addElement( 'https://bzmall.oss-cn-shanghai.aliyuncs.com/back.jpg', this.length, this.height, 0, 0, this.length/2-this.width, 0, Math.PI, 0) );//back

                group.add( this.addElement( 'https://bzmall.oss-cn-shanghai.aliyuncs.com/left.jpg', this.width, this.height, -this.length/2, 0, this.length-this.width, 0, -Math.PI/2, 0 ) );//left
                group.add( this.addElement( 'https://bzmall.oss-cn-shanghai.aliyuncs.com/right.jpg', this.width, this.height, this.length/2, 0, 0, 0, Math.PI/2, 0 ) );//right
                
                group.add( this.addElement( 'https://bzmall.oss-cn-shanghai.aliyuncs.com/top.jpg', this.length, this.width, 0, this.length/2, this.length-this.width, -Math.PI/2, 0, 0 ) );//top
                group.add( this.addElement( 'https://bzmall.oss-cn-shanghai.aliyuncs.com/bottom.jpg', this.length, this.width, 0, -this.length/2+(this.length-this.height), 0, Math.PI/2, 0, 0 ) );//bottom

                this.scene.add( group );

                this.controls = new TrackballControls( this.camera, this.renderer.domElement );
                this.controls.rotateSpeed = 0.8;
                this.controls.zoomSpeed = 0.2;
                this.controls.noRoll = true;
                this.controls.staticMoving = true;
                this.controls.minDistance = 500;
                this.controls.maxDistance = 1200;

                window.addEventListener( 'resize', this.onWindowResize, false );

                // Block iframe events when dragging camera

                var blocker = this.$refs.blocker;
                blocker.style.display = 'none';

                this.controls.addEventListener( 'start', function () {

                    blocker.style.display = '';

                } );
                this.controls.addEventListener( 'end', function () {

                    blocker.style.display = 'none';

                } );

            },
            addElement (src, width, height, x, y, z, rx, ry, rz ) {
                var div = document.createElement( 'div' );
                div.style.width = this.length + 'px';
                div.style.height = this.length + 'px';
                div.style.backgroundColor = 'rgba(255,255,255,0)';

                var img = document.createElement( 'img' );
                img.style.width = width + 'px';
                img.style.height = height + 'px';
                img.src = src;
                div.appendChild( img );

                var object = new CSS3DObject( div );
                object.position.set( x, y, z );
                object.rotation.x = rx;
                object.rotation.y = ry;
                object.rotation.z = rz;

                return object;
            },
            onWindowResize() {
                this.camera.aspect = window.innerWidth / window.innerHeight;
                this.camera.updateProjectionMatrix();
                this.renderer.setSize( window.innerWidth, window.innerHeight );
            },
            animate() {
                requestAnimationFrame( this.animate );
                this.controls.update();
                this.renderer.render( this.scene, this.camera );
            }
        }
    }
</script>

<style scoped>
    .container {
        background-image: url(../../assets/750.png);
        background-repeat: no-repeat;
        background-size: cover;
    }
    .blocker {
        position: absolute;
        top: 0;
        left: 0;
        bottom: 0;
        right: 0;
    }
    .exterior {
        position: fixed;
        right: 20px;
        bottom: 130px;
        color: #fff;
        width: 36px;
        height: 36px;
        border: 2px solid #fff;
        border-radius: 36px;
        line-height: 36px;
        text-align: center;
    }
    .sellingPoint {
        position: fixed;
        right: 20px;
        bottom: 80px;
        color: #fff;
        width: 36px;
        height: 36px;
        border: 2px solid #fff;
        border-radius: 36px;
        line-height: 36px;
        text-align: center;
    }
    .parameter {
        position: fixed;
        right: 20px;
        bottom: 30px;
        color: #fff;
        width: 36px;
        height: 36px;
        border: 2px solid #fff;
        border-radius: 36px;
        line-height: 36px;
        text-align: center;
    }
</style>
