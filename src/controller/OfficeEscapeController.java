package controller;


import model.room.Room;
import view.OfficeEscapeView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class OfficeEscapeController {

    private static final int DEFAULT_ROW_DIM = 4;
    private static final int DEFAULT_COL_DIM = 4;

    public OfficeEscapeController() throws IOException {
        run();
    }


    public void run() {

        GraphManager graphManager = new GraphManager(DEFAULT_ROW_DIM, DEFAULT_COL_DIM);
        RoomManager roomManager = new RoomManager(graphManager.getConnectedRoomsMap(), DEFAULT_ROW_DIM);
        HashMap<Room, HashSet<Room>> roomsMap = roomManager.extractRoomsMap();
        List<Room> roomsList = roomManager.extractRoomsList();

        EventQueue.invokeLater(() -> {
            try {
                new OfficeEscapeView(roomsList, roomsMap);
            } catch (final ClassNotFoundException
                    | InstantiationException
                    | IllegalAccessException
                    | UnsupportedLookAndFeelException
                    | IOException
                    | FontFormatException e) {
                e.printStackTrace();
            }
        });
    }

}
