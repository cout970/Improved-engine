package com.cout970.tests

import com.cout970.engine.Loader
import com.cout970.engine.camera.Camera
import com.cout970.engine.camera.OrthoCamera
import com.cout970.engine.camera.PerspectiveCamera
import com.cout970.engine.event.EventManager
import com.cout970.engine.renderer.Renderer
import com.cout970.engine.resource.EngineDomain
import com.cout970.engine.resource.Resource
import com.cout970.engine.shader.ShaderProgram
import com.cout970.engine.shader.UniformVariable
import com.cout970.engine.tessellator.Tessellator
import com.cout970.engine.tessellator.VAO
import com.cout970.engine.texture.Texture
import com.cout970.engine.texture.TextureLoader
import com.cout970.engine.util.math.xi
import com.cout970.engine.util.math.yi
import com.cout970.engine.window.IWindow
import com.cout970.engine.window.WindowBuilder
import org.joml.Matrix4d
import org.joml.Vector2d
import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL11

/**
 * Created by cout970 on 31/07/2016.
 */

fun main(args: Array<String>) {

    val builder = WindowBuilder()
    builder.pos = Vector2d(20.0, 30.0)
    builder.size = Vector2d(500.0, 500.0)
    builder.title = "Tile 1"

    builder.loadDefaultProperties()

    Loader.init()

    val window = builder.create()

    Loader.initOpenGL()

    val res = Resource(EngineDomain, "test_texture2.png")
    val textureData = TextureLoader.loadTexture(res)
    val texture = TextureLoader.uploadTexture2D(textureData)

    texture.minFilter = GL11.GL_NEAREST
    texture.magFilter = GL11.GL_NEAREST

    texture.wrapS = Texture.REPEAT
    texture.wrapT = Texture.REPEAT

    val renderer = Renderer()
    val vao = CameraTest.createVao()
    val camera = PerspectiveCamera(Math.toRadians(60.0), window.box.size.x / window.box.size.y, 0.001, 100.0)
//    val camera: Camera = OrthoCamera(window.box, 0.001, 100.0)

    camera.position.z += 0.5

    while (true) {

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)
        GL11.glClearDepth(1.0)

        GL11.glViewport(0, 0, window.box.size.xi, window.box.size.yi)
        if (camera is PerspectiveCamera) {
            camera.aspect = window.box.size.x / window.box.size.y
            CameraTest.perpesctive(window, camera)
        } else {
            CameraTest.ortho(window, camera as OrthoCamera)
        }

        texture.bind()
        CameraTest.BasicShader.start()
        CameraTest.BasicShader.setPMatrix(camera.getProjection())
        CameraTest.BasicShader.setVMatrix(camera.getView())
        renderer.render(vao)
        CameraTest.BasicShader.stop()

        GL11.glFlush()
        //actualizacion de frame
        window.swapBuffers()
        //gestion de eventos como el teclado
        EventManager.pollEvents()
        if (window.shouldClose()) break
    }
    Loader.close()
}


object CameraTest {

    fun checkInput(win: IWindow, cam: Camera) {

        if (EventManager.isKeyPressed(win, GLFW.GLFW_KEY_S)) {
            cam.position.y += 0.1
        }
        if (EventManager.isKeyPressed(win, GLFW.GLFW_KEY_W)) {
            cam.position.y -= 0.1
        }

        if (EventManager.isKeyPressed(win, GLFW.GLFW_KEY_A)) {
            cam.position.x += 0.1
        }
        if (EventManager.isKeyPressed(win, GLFW.GLFW_KEY_D)) {
            cam.position.x -= 0.1
        }

        if (EventManager.isKeyPressed(win, GLFW.GLFW_KEY_Q)) {
            cam.position.z += 0.1
        }
        if (EventManager.isKeyPressed(win, GLFW.GLFW_KEY_E)) {
            cam.position.z -= 0.1
        }

        if (EventManager.isKeyPressed(win, GLFW.GLFW_KEY_LEFT)) {
            cam.rotations.y += 0.05
        }
        if (EventManager.isKeyPressed(win, GLFW.GLFW_KEY_RIGHT)) {
            cam.rotations.y -= 0.05
        }

        if (EventManager.isKeyPressed(win, GLFW.GLFW_KEY_UP)) {
            cam.rotations.x += 0.05
        }
        if (EventManager.isKeyPressed(win, GLFW.GLFW_KEY_DOWN)) {
            cam.rotations.x -= 0.05
        }
    }

    fun ortho(win: IWindow, cam: OrthoCamera) {
        if (EventManager.isKeyPressed(win, GLFW.GLFW_KEY_1)) {
            cam.zNear += 0.1
        }
        if (EventManager.isKeyPressed(win, GLFW.GLFW_KEY_2)) {
            cam.zNear -= 0.1
        }

        if (EventManager.isKeyPressed(win, GLFW.GLFW_KEY_3)) {
            cam.zFar += 0.1
        }
        if (EventManager.isKeyPressed(win, GLFW.GLFW_KEY_4)) {
            cam.zFar -= 0.1
        }
        checkInput(win, cam)
    }

    fun perpesctive(win: IWindow, cam: PerspectiveCamera) {
        if (EventManager.isKeyPressed(win, GLFW.GLFW_KEY_1)) {
            cam.fov += 0.1
        }
        if (EventManager.isKeyPressed(win, GLFW.GLFW_KEY_2)) {
            cam.fov -= 0.1
        }

        if (EventManager.isKeyPressed(win, GLFW.GLFW_KEY_3)) {
            cam.nearPlane += 0.1
        }
        if (EventManager.isKeyPressed(win, GLFW.GLFW_KEY_4)) {
            cam.nearPlane -= 0.1
        }

        if (EventManager.isKeyPressed(win, GLFW.GLFW_KEY_5)) {
            cam.farPlane += 0.1
        }
        if (EventManager.isKeyPressed(win, GLFW.GLFW_KEY_6)) {
            cam.farPlane -= 0.1
        }
        checkInput(win, cam)
    }

    fun createVao(): VAO {

        val t = Tessellator

        val start = -0.5f
        val end__ = 0.5f

        t.startCompilation(GL11.GL_QUADS)
        t.set(0, start, start, 0.001).set(1, 0f, 1f).set(2, 0, 1, 0).endVertex()
        t.set(0, end__, start, 0.001).set(1, 1f, 1f).set(2, 0, 1, 0).endVertex()
        t.set(0, end__, end__, 0.001).set(1, 1f, 0f).set(2, 0, 1, 0).endVertex()
        t.set(0, start, end__, 0.001).set(1, 0f, 0f).set(2, 0, 1, 0).endVertex()
        return t.endCompilation()
    }

    var vertexCode =
            """
#version 400 core

in vec3 pos;
in vec2 tex;
in vec3 norm;

out vec2 tex_coord;
out vec3 norm_vec;

uniform mat4 vMatrix;
uniform mat4 pMatrix;

void main(void){
    gl_Position = pMatrix * vMatrix * vec4(pos, 1.0);
    tex_coord = tex;
}
"""

    var fragmentCode =
            """
#version 400 core

in vec2 tex_coord;

out vec4 color;

uniform sampler2D textureSampler;

void main(void){
    color = texture(textureSampler, tex_coord);
    float mul = 0.9;
    float sum = 0.25;
    color = vec4(color.xyz * mul + sum, 1.0);
}
"""

    object BasicShader : ShaderProgram(vertexCode, fragmentCode) {

        lateinit var view: UniformVariable
        lateinit var projection: UniformVariable

        override fun bindAttributes() {
            bindAttribute(0, "pos")
            bindAttribute(1, "tex")
            bindAttribute(2, "norm")
        }

        override fun loadUniformLocations() {
            view = createUniformVariable("vMatrix")
            projection = createUniformVariable("pMatrix")
        }

        fun setPMatrix(m: Matrix4d) {
            projection.setMatrix4(m)
        }

        fun setVMatrix(m: Matrix4d) {
            view.setMatrix4(m)
        }
    }
}