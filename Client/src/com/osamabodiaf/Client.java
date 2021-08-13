package com.osamabodiaf;

public class Client {
    private ClientChatBuffer clientChatBuffer;
    private ClientGUI clientGUI;
    private ClientGUIHandler clientGUIHandler;
    private ClientWorker clientWorker;

    public Client() {
        clientChatBuffer = new ClientChatBuffer();
        clientGUI = new ClientGUI(this);
        clientGUIHandler = new ClientGUIHandler(this);
        clientWorker = new ClientWorker(this);
    }

    public ClientGUI getClientGUI() {
        return clientGUI;
    }

    public ClientWorker getClientWorker() {
        return clientWorker;
    }

    public ClientChatBuffer getClientChatBuffer() {
        return clientChatBuffer;
    }

    public ClientGUIHandler getClientGUIHandler() {
        return clientGUIHandler;
    }
}
