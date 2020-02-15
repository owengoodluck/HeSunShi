<template>
    <div class="container" ref="container"></div>
</template>

<script>
    import * as THREE from 'three';
    import {CSS3DRenderer,CSS3DObject} from 'three-css3drenderer';
    export default {
        data() {
            return {
                camera: null,
                scene: null,
                renderer: null,
                target: null,
                lon: 90,
                lat: 0,
                phi: 0,
                theta: 0,
                touchX: 0,
                touchY: 0
            }
        },
        mounted() {
            this.target = new THREE.Vector3();
            this.init();
            this.animate();
        },
        methods: {
            init() {
                var container = this.$refs.container;
                this.camera = new THREE.PerspectiveCamera( 75, window.innerWidth / window.innerHeight, 1, 1000 );
                this.scene = new THREE.Scene();
                var sides = [
                    {
                        url: 'https://bzmall.oss-cn-shanghai.aliyuncs.com/left.jpg',
                        position: [ - 512, 0, 0 ],
                        rotation: [ 0, Math.PI / 2, 0 ]
                    },
                    {
                        url: 'https://bzmall.oss-cn-shanghai.aliyuncs.com/right.jpg',
                        position: [ 512, 0, 0 ],
                        rotation: [ 0, - Math.PI / 2, 0 ]
                    },
                    {
                        url: 'https://bzmall.oss-cn-shanghai.aliyuncs.com/top.jpg',
                        position: [ 0, 512, 0 ],
                        rotation: [ Math.PI / 2, 0, Math.PI ]
                    },
                    {
                        url: 'https://bzmall.oss-cn-shanghai.aliyuncs.com/bottom.jpg',
                        position: [ 0, - 512, 0 ],
                        rotation: [ - Math.PI / 2, 0, Math.PI ]
                    },
                    {
                        url: 'https://bzmall.oss-cn-shanghai.aliyuncs.com/back.jpg',
                        position: [ 0, 0, 512 ],
                        rotation: [ 0, Math.PI, 0 ]
                    },
                    {
                        url: 'https://bzmall.oss-cn-shanghai.aliyuncs.com/front.jpg',
                        position: [ 0, 0, - 512 ],
                        rotation: [ 0, 0, 0 ]
                    }
                ];
                for ( var i = 0; i < sides.length; i ++ ) {
                    var side = sides[ i ];
                    var element = document.createElement( 'img' );
                    element.width = 1026; // 2 pixels extra to close the gap.
                    element.src = side.url;
                    var object = new CSS3DObject( element );
                    object.position.fromArray( side.position );
                    object.rotation.fromArray( side.rotation );
                    this.scene.add( object );
                }
                this.renderer = new CSS3DRenderer();
                this.renderer.setSize( window.innerWidth, window.innerHeight );
                container.appendChild( this.renderer.domElement );

                document.addEventListener( 'mousedown', this.onDocumentMouseDown, false );
                document.addEventListener( 'wheel', this.onDocumentMouseWheel, false );
                document.addEventListener( 'touchstart', this.onDocumentTouchStart, false );
                document.addEventListener( 'touchmove', this.onDocumentTouchMove, false );
                window.addEventListener( 'resize', this.onWindowResize, false );
            },
            onWindowResize() {
                this.camera.aspect = window.innerWidth / window.innerHeight;
                this.camera.updateProjectionMatrix();
                this.renderer.setSize( window.innerWidth, window.innerHeight );
            },
            onDocumentMouseDown( event ) {
                event.preventDefault();
                document.addEventListener( 'mousemove', this.onDocumentMouseMove, false );
                document.addEventListener( 'mouseup', this.onDocumentMouseUp, false );
            },
            onDocumentMouseMove( event ) {
                var movementX = event.movementX || event.mozMovementX || event.webkitMovementX || 0;
                var movementY = event.movementY || event.mozMovementY || event.webkitMovementY || 0;
                this.lon -= movementX * 0.1;
                this.lat += movementY * 0.1;
            },
            onDocumentMouseUp() {
                document.removeEventListener( 'mousemove', this.onDocumentMouseMove );
                document.removeEventListener( 'mouseup', this.onDocumentMouseUp );
            },
            onDocumentMouseWheel( event ) {
                var fov = this.camera.fov + event.deltaY * 0.05;
                this.camera.fov = THREE.Math.clamp( fov, 10, 75 );
                this.camera.updateProjectionMatrix();
            },
            onDocumentTouchStart( event ) {
                //event.preventDefault();
                var touch = event.touches[ 0 ];
                this.touchX = touch.screenX;
                this.touchY = touch.screenY;
            },
            onDocumentTouchMove( event ) {
                //event.preventDefault();
                var touch = event.touches[ 0 ];
                this.lon -= ( touch.screenX - this.touchX ) * 0.1;
                this.lat += ( touch.screenY - this.touchY ) * 0.1;
                this.touchX = touch.screenX;
                this.touchY = touch.screenY;
            },
            animate() {
                requestAnimationFrame( this.animate );
                this.lon += 0.1;
                this.lat = Math.max( - 85, Math.min( 85, this.lat ) );
                this.phi = THREE.Math.degToRad( 90 - this.lat );
                this.theta = THREE.Math.degToRad( this.lon );
                this.target.x = Math.sin( this.phi ) * Math.cos( this.theta );
                this.target.y = Math.cos( this.phi );
                this.target.z = Math.sin( this.phi ) * Math.sin( this.theta );
                this.camera.lookAt( this.target );
                this.renderer.render( this.scene, this.camera );
            }
        }
    }
</script>

<style scoped>
    .container {
        position: fixed;
        top: 0;
    }
</style>
