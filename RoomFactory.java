public class RoomFactory {
    public static Room createRoom(String RoomType) {
        if(RoomType=="DeluxeDoubleRoom") {
            return new DeluxeDoubleRoom();
        }

        else if(RoomType=="LuxuryDoubleRoom") {
            return new LuxuryDoubleRoom();
        }

        else if(RoomType=="LuxurySingleRoom") {
            return new LuxurySingleRoom();
        }

        else {
            return new DeluxeSingleRoom();
        }
    }
}
