/*
 * The MIT License
 *
 * Copyright 2016 Thibault Debatty.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package aptgraph.server;

import aptgraph.core.Request;
import info.debatty.java.graphs.Graph;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.LinkedList;
import junit.framework.TestCase;

/**
 *
 * @author Thibault Debatty
 */
public class RequestHandlerTest extends TestCase {

    /**
     * Test of test method, of class RequestHandler.
     */
    public void testTest() throws IOException, ClassNotFoundException {
        System.out.println("test");
        InputStream graph_stream =
                getClass().getResourceAsStream("/dummy_graph.ser");

        ObjectInputStream input = new ObjectInputStream(
                new BufferedInputStream(graph_stream));
        HashMap<String, LinkedList<Graph<Request>>> user_graphs =
              (HashMap<String, LinkedList<Graph<Request>>>) input.readObject();
        input.close();
        
        RequestHandler handler = new RequestHandler(user_graphs);
        handler.test();
    }

    /**
     * Test of dummy method, of class RequestHandler.
     */
    public void testDummy() throws IOException, ClassNotFoundException {
        System.out.println("dummy");
        InputStream graph_stream =
                getClass().getResourceAsStream("/dummy_graph.ser");

        ObjectInputStream input = new ObjectInputStream(
                new BufferedInputStream(graph_stream));
        HashMap<String, LinkedList<Graph<Request>>> user_graphs =
              (HashMap<String, LinkedList<Graph<Request>>>) input.readObject();
        input.close();

        RequestHandler handler = new RequestHandler(user_graphs);
        handler.dummy();
    }

    /**
     * Test of analyze method, of class RequestHandler.
     */
    public void testAnalyze() throws IOException, ClassNotFoundException {
        System.out.println("analyze");
        InputStream graph_stream =
                getClass().getResourceAsStream("/dummy_graph.ser");

        ObjectInputStream input = new ObjectInputStream(
                new BufferedInputStream(graph_stream));
        HashMap<String, LinkedList<Graph<Request>>> user_graphs =
              (HashMap<String, LinkedList<Graph<Request>>>) input.readObject();
        input.close();

        RequestHandler handler = new RequestHandler(user_graphs);
        handler.analyze("127.0.0.1", new double[]{0.7, 0.3},
                new double[]{0.8, 0.2}, 10.0, 10);
    }
}
