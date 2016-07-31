package com.cout970.engine.tessellator;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

/**
 * Created by cout970 on 09/05/2016.
 */
public class VAOFactory {

    protected int id;
    protected int vertexCount;
    protected List<Integer> vbos;
    //indices
    protected IntBuffer indices;
    //atributos del vao
    protected List<FloatBuffer> attribBuffers;
    protected List<Integer> attibCounts;

    protected int drawMode;
    protected boolean useElements;
    private int vboCount = 1;

    //drawMode, por ejemplo, GL_TRIANGLES
    public VAOFactory(int drawMode) {
        this.drawMode = drawMode;
        vbos = new LinkedList<>();
        useElements = false;
        attribBuffers = new ArrayList<>();
        attibCounts = new ArrayList<>();
    }

    public void addIndices(IntBuffer indices) {
        this.indices = indices;
        useElements = true;
    }

    //buffer: buffer de datos
    //vertexElements numero de datos por vertice, por ejemplo 3, (x,y,z)
    public void addAttribArray(FloatBuffer buffer, int vertexElements) {
        attribBuffers.add(buffer);
        attibCounts.add(vertexElements);
    }

    public VAO build() {
        id = glGenVertexArrays();
        glBindVertexArray(id);

        //indices del vao
        if (indices != null){
            int vbo = glGenBuffers();
            vertexCount = indices.remaining();
            useElements = true;
            vbos.add(vbo);

            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vbo);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);
        }
        //atributos del vao
        for (int i = 0; i < attribBuffers.size(); i++) {
            int vbo = glGenBuffers();
            vbos.add(vbo);

            glBindBuffer(GL_ARRAY_BUFFER, vbo);
            glBufferData(GL_ARRAY_BUFFER, attribBuffers.get(i), GL_STATIC_DRAW);
            //argumentos: attrib, num vertices, normalize, jump(stride), offset
            glVertexAttribPointer(i, attibCounts.get(i), GL_FLOAT, false, 0, 0);
            glBindBuffer(GL_ARRAY_BUFFER, 0);
        }
        glBindVertexArray(0);

        return new VAO(id, vertexCount, attribBuffers.size()+1, vbos, drawMode, useElements);
    }
}
