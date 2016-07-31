package com.cout970.tests

import com.cout970.engine.Loader
import com.cout970.engine.camera.Camera
import com.cout970.engine.camera.PerspectiveCamera
import com.cout970.engine.event.EventManager
import com.cout970.engine.renderer.Renderer
import com.cout970.engine.resource.EngineDomain
import com.cout970.engine.resource.Resource
import com.cout970.engine.shader.ShaderPreprocesor
import com.cout970.engine.shader.ShaderProgram
import com.cout970.engine.shader.UniformVariable
import com.cout970.engine.tessellator.Tessellator
import com.cout970.engine.tessellator.VAO
import com.cout970.engine.texture.Texture
import com.cout970.engine.texture.TextureLoader
import com.cout970.engine.util.math.rotate
import com.cout970.engine.util.math.xi
import com.cout970.engine.util.math.yi
import com.cout970.engine.window.IWindow
import com.cout970.engine.window.WindowBuilder
import org.joml.Matrix4d
import org.joml.Vector2d
import org.joml.Vector3d
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
    val vao = LightTest.createVao()
    val camera = PerspectiveCamera(Math.toRadians(60.0), window.box.size.x / window.box.size.y, 0.001, 100.0)

    camera.position.z += 0.5
    GL11.glEnable(GL11.GL_DEPTH_TEST)
    var i = 0.0

    while (true) {

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)
        GL11.glClearDepth(1.0)

        GL11.glViewport(0, 0, window.box.size.xi, window.box.size.yi)
        if (camera is PerspectiveCamera) {
            camera.aspect = window.box.size.x / window.box.size.y
            LightTest.perpesctive(window, camera)
        }

        i += 0.01
        val lightPos = Vector3d(8.0, 5.0, 1.0).rotate(i, -1.0, 1.0, -1.0)
        texture.bind()
        LightTest.BasicShader.start()
        LightTest.BasicShader.setLight(lightPos, Vector3d(1.0))
        LightTest.BasicShader.setPMatrix(camera.getProjection())
        LightTest.BasicShader.setVMatrix(camera.getView())
        renderer.render(vao)
        val t = Tessellator

        t.startDrawing(GL11.GL_LINES)
        t.set(0, 0, 0, 0).set(1, 0, 0).set(2, 0, 1, 0).endVertex()
        t.set(0, lightPos.x, lightPos.y, lightPos.z).set(1, 0, 0).set(2, 0, 1, 0).endVertex()
        t.endDrawing()
        t.draw(renderer)

        LightTest.BasicShader.stop()

        GL11.glFlush()
        //actualizacion de frame
        window.swapBuffers()
        //gestion de eventos como el teclado
        EventManager.pollEvents()
        if (window.shouldClose()) break
    }
    Loader.close()
}


object LightTest {

    fun checkInput(win: IWindow, cam: Camera) {

        if (EventManager.isKeyPressed(win, GLFW.GLFW_KEY_S)) {
            cam.position.y -= 0.1
        }
        if (EventManager.isKeyPressed(win, GLFW.GLFW_KEY_W)) {
            cam.position.y += 0.1
        }

        if (EventManager.isKeyPressed(win, GLFW.GLFW_KEY_A)) {
            cam.position.x -= 0.1
        }
        if (EventManager.isKeyPressed(win, GLFW.GLFW_KEY_D)) {
            cam.position.x += 0.1
        }

        if (EventManager.isKeyPressed(win, GLFW.GLFW_KEY_Q)) {
            cam.position.z -= 0.1
        }
        if (EventManager.isKeyPressed(win, GLFW.GLFW_KEY_E)) {
            cam.position.z += 0.1
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

        val start = 0f
        val end__ = 1f

        t.startCompilation(GL11.GL_QUADS)

        t.set(0, start, start, 0.0).set(1, 0f, 1f).set(2, 0, 0, -1).endVertex()
        t.set(0, end__, start, 0.0).set(1, 1f, 1f).set(2, 0, 0, -1).endVertex()
        t.set(0, end__, end__, 0.0).set(1, 1f, 0f).set(2, 0, 0, -1).endVertex()
        t.set(0, start, end__, 0.0).set(1, 0f, 0f).set(2, 0, 0, -1).endVertex()

        t.set(0, start, 0.0, start).set(1, 0f, 1f).set(2, 0, -1, 0).endVertex()
        t.set(0, end__, 0.0, start).set(1, 1f, 1f).set(2, 0, -1, 0).endVertex()
        t.set(0, end__, 0.0, end__).set(1, 1f, 0f).set(2, 0, -1, 0).endVertex()
        t.set(0, start, 0.0, end__).set(1, 0f, 0f).set(2, 0, -1, 0).endVertex()

        t.set(0, 0.0, start, start).set(1, 0f, 1f).set(2, -1, 0, 0).endVertex()
        t.set(0, 0.0, end__, start).set(1, 1f, 1f).set(2, -1, 0, 0).endVertex()
        t.set(0, 0.0, end__, end__).set(1, 1f, 0f).set(2, -1, 0, 0).endVertex()
        t.set(0, 0.0, start, end__).set(1, 0f, 0f).set(2, -1, 0, 0).endVertex()

        t.set(0, start, start, 1.0).set(1, 0f, 1f).set(2, 0, 0, 1).endVertex()
        t.set(0, end__, start, 1.0).set(1, 1f, 1f).set(2, 0, 0, 1).endVertex()
        t.set(0, end__, end__, 1.0).set(1, 1f, 0f).set(2, 0, 0, 1).endVertex()
        t.set(0, start, end__, 1.0).set(1, 0f, 0f).set(2, 0, 0, 1).endVertex()

        t.set(0, start, 1.0, start).set(1, 0f, 1f).set(2, 0, 1, 0).endVertex()
        t.set(0, end__, 1.0, start).set(1, 1f, 1f).set(2, 0, 1, 0).endVertex()
        t.set(0, end__, 1.0, end__).set(1, 1f, 0f).set(2, 0, 1, 0).endVertex()
        t.set(0, start, 1.0, end__).set(1, 0f, 0f).set(2, 0, 1, 0).endVertex()

        t.set(0, 1.0, start, start).set(1, 0f, 1f).set(2, 1, 0, 0).endVertex()
        t.set(0, 1.0, end__, start).set(1, 1f, 1f).set(2, 1, 0, 0).endVertex()
        t.set(0, 1.0, end__, end__).set(1, 1f, 0f).set(2, 1, 0, 0).endVertex()
        t.set(0, 1.0, start, end__).set(1, 0f, 0f).set(2, 1, 0, 0).endVertex()

        return t.endCompilation()
    }

    object BasicShader : ShaderProgram(ShaderPreprocesor.loadShader(Resource(EngineDomain, "./shader_test_light_vertex.glsl")), ShaderPreprocesor.loadShader(Resource(EngineDomain, "./shader_test_light_fragment.glsl"))) {

        lateinit var view: UniformVariable
        lateinit var projection: UniformVariable

        lateinit var lightPos: UniformVariable
        lateinit var lightColor: UniformVariable

        override fun bindAttributes() {
            bindAttribute(0, "pos")
            bindAttribute(1, "tex")
            bindAttribute(2, "norm")
        }

        override fun loadUniformLocations() {
            view = createUniformVariable("vMatrix")
            projection = createUniformVariable("pMatrix")
            lightPos = createUniformVariable("lightPos")
            lightColor = createUniformVariable("lightColor")
        }

        fun setLight(pos: Vector3d, color: Vector3d) {
            lightPos.setVector3(pos)
            lightColor.setVector3(color)
        }

        fun setPMatrix(m: Matrix4d) {
            projection.setMatrix4(m)
        }

        fun setVMatrix(m: Matrix4d) {
            view.setMatrix4(m)
        }
    }
}