// test/O/InstrumentPlayerTest.java
package O;

import org.junit.Test;
import org.junit.Before;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import static org.junit.Assert.*;
import O._new.*;

public class InstrumentPlayerTest {

    private InstrumentPlayer player;
    private ByteArrayOutputStream out;

    @Before
    public void setUp() {
        player = new InstrumentPlayer();
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    public void play_withGuitar_shouldPrintGuitarMessage() {
        player.play(new Guitar());
        assertTrue(out.toString().contains("Strumming the guitar"));
    }

    @Test
    public void play_withDrums_shouldPrintDrumsMessage() {
        player.play(new Drums());
        assertTrue(out.toString().contains("Beating the drums"));
    }

    @Test
    public void play_withPiano_shouldPrintPianoMessage() {
        player.play(new Piano());
        assertTrue(out.toString().contains("Playing the piano"));
    }

    @Test
    public void play_withCustomInstrument_shouldExecuteItsOwnPlay() {
        // Verificamos el principio O: un nuevo instrumento funciona sin tocar nada
        Instrument violin = () -> System.out.println("Playing the violin");
        player.play(violin);
        assertTrue(out.toString().contains("Playing the violin"));
    }

}