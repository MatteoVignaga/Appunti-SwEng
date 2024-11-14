import org.assertj.core.api.AssertionsForClassTypes;
import org.example.Box;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.mockito.Mockito.*;

//questa notazione serve con Junit5 per evitare di dover istanziare i mock prima di ogni test
@ExtendWith(MockitoExtension.class)
public class BoxTest {
    //valido solo per variabili globali!!
    @Mock List<String> mockedList;
    @InjectMocks Box SUT;

    @Test
    public void injectionTest() {
        //mockedList è un mock, quindi serve stubbarlo
        when(mockedList.toString()).thenReturn("Banana, Mela, Pera");

        //mi assicuro che SUT faccia ciò che mi interessa se mockedList ha un comportamento controllato
        assertThat(SUT.toString()).isEqualTo("Banana, Mela, Pera");
    }

    @Test
    public void spyTest() {
        Box spy = spy(SUT);
        spy.setContentList(mockedList);

        //i seguenti sono equivalenti, ma nel secondo mi va bene qualsiasi input al metodo
        verify(spy).setContentList(mockedList);
        verify(spy, atLeastOnce()).setContentList(any());
    }

    //non funziona, non so perchè
    @Test
    public void extractionTest() {
        when(mockedList.iterator()).thenReturn(List.of("banana", "mela", "pera").iterator());
        AssertionsForClassTypes.assertThat(SUT).extracting("boxContent", as(LIST)).containsExactlyInAnyOrder("mela", "banana", "pera");
    }
}
