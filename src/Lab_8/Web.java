package Lab_8;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Web {
    public enum Type{SERVER, CLIENT}

    private final Type type;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader consoleReader;
    private BufferedReader in;
    private BufferedWriter out;
    private ObjectOutputStream oOut;
    private ObjectInputStream oIn;

    public Web(Type tp, int port, String host) {
        type = tp;
        if (type == Type.CLIENT) {
            InitClient(host, port);
        }
        else {
            InitServer(port);
        }

    }
    public boolean Send(String msg) {
        try {
            oOut.writeUTF(msg);
            out.flush();
        }
        catch (IOException ex)
        {
            System.err.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public String Read() {
        try
        {
            return oIn.readUTF();
        }
        catch (IOException ex)
        {
            System.err.println(ex.getMessage());
            return "";
        }
    }

    public boolean SendObject(Object ob) {
        try
        {
            oOut.writeObject(ob);
            out.flush();
        }
        catch (IOException ex)
        {
            System.err.println(ex.getMessage());
            return false;
        }
        return true;
    }
    public Object ReadObject() {
        try
        {
            return oIn.readObject();
        }
        catch (IOException | ClassNotFoundException ex)
        {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    private boolean InitClient(String host, int port) {
        if (type != Type.CLIENT)
        {
            System.err.println("Error in type");
            return false;
        }

        try
        {
            clientSocket = new Socket("localhost", port);
            consoleReader = new BufferedReader(new InputStreamReader(System.in));
            oOut = new ObjectOutputStream(clientSocket.getOutputStream()); // почему зависит от порядка?
            oIn = new ObjectInputStream(clientSocket.getInputStream());

        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
            try {
                clientSocket.close();
                consoleReader.close();
                in.close();
                out.close();
            }
            catch (IOException ex)
            {
                System.err.println(ex.getMessage());
            }
            return false;
        }

        return true;
    }
    private boolean InitServer(int port)
    {
        if (type != Type.SERVER)
        {
            System.err.println("Error in type");
            return false;
        }

        try
        {
            serverSocket = new ServerSocket(port);
            consoleReader = new BufferedReader(new InputStreamReader(System.in));
            clientSocket = serverSocket.accept();
            oOut = new ObjectOutputStream(clientSocket.getOutputStream()); // почему зависит от порядка?
            oIn = new ObjectInputStream(clientSocket.getInputStream());
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
            try {
                clientSocket.close();
                consoleReader.close();
                in.close();
                out.close();
            }
            catch (IOException ex)
            {
                System.err.println(e.getMessage());
            }
            return false;
        }

        return true;
    }
    public void close()
    {
        try {
            clientSocket.close();
            consoleReader.close();
            in.close();
            out.close();
        }
        catch (IOException ex)
        {
            System.err.println(ex.getMessage());
        }
    }

}
