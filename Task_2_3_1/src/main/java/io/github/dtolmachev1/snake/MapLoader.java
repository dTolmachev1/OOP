package io.github.dtolmachev1.snake;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.Writer;

/**
 * <p> Class for map loader.</p>
 */
public class MapLoader {
    private final Gson gson;  // for json serialization

    /**
     * <p>Default constructor to initialize internal structures.</p>
     */
    public MapLoader() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        this.gson = builder.create();
    }

    /**
     * <p>Deserializes content of the board from the given reader stream.</p>
     *
     * @param reader Stream for deserialization.
     * @return Newly created board.
     */
    public Board loadMap(Reader reader) {
        return this.gson.fromJson(reader, Board.class);
    }

    /**
     * <p>Serializes content of the specified board to the given writer stream.</p>
     *
     * @param board Board to be serialized.
     * @param writer Stream for serialization.
     */
    public void storeMap(Board board, Writer writer) {
        this.gson.toJson(board, writer);
    }
}
