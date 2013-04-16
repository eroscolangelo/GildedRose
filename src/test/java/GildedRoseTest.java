import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;


public class GildedRoseTest {

	@Test
	public void testTheTruth() {

        GildedRose.items = new ArrayList<Item>();
        GildedRose.items.add(new Item("+5 Dexterity Vest", 10, 20));
        GildedRose.items.add(new Item("Aged Brie", 2, 0));
        GildedRose.items.add(new Item("Elixir of the Mongoose", 5, 7));
        GildedRose.items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        GildedRose.items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        GildedRose.items.add(new Item("Conjured Mana Cake", 3, 6));
		GildedRose.updateQuality();

	}

    @Test
    public void updateQualityOnAgedBrie(){
        Item item = new Item("Aged Brie", 2, 2);
        GildedRose.updateQualityOnItem(item);
        assertThat(item.getQuality(), is(3));
        assertThat(item.getSellIn(), is(1));
    }

    @Test
    public void updateQualityOnAgedBrieExpired(){
        Item item = new Item("Aged Brie", 0, 2);
        GildedRose.updateQualityOnItem(item);
        assertThat(item.getQuality(), is(4));
        assertThat(item.getSellIn(), is(-1));
    }

    @Test
    public void updateQualityOnAgedBrieAtMaxQuality(){
        Item item = new Item("Aged Brie", 0, 50);
        GildedRose.updateQualityOnItem(item);
        assertThat(item.getQuality(), is(50));
        assertThat(item.getSellIn(), is(-1));
    }


    @Test
    public void updateQualityOnSulfuras(){
        Item item = new Item("Sulfuras, Hand of Ragnaros", 2, 2);
        GildedRose.updateQualityOnItem(item);
        assertThat(item.getQuality(), is(2));
        assertThat(item.getSellIn(), is(2));
    }

    @Test
    public void updateQualityOnSulfurasExpired(){
        Item item = new Item("Sulfuras, Hand of Ragnaros", 0, 2);
        GildedRose.updateQualityOnItem(item);
        assertThat(item.getQuality(), is(2));
        assertThat(item.getSellIn(), is(0));
    }

    @Test
    public void updateQualityOnSulfurasAtMinQuality(){
        Item item = new Item("Sulfuras, Hand of Ragnaros", 0, 0);
        GildedRose.updateQualityOnItem(item);
        assertThat(item.getQuality(), is(0));
        assertThat(item.getSellIn(), is(0));
    }

    @Test
    public void updateQualityOnBackstagePasses(){
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 12, 3);
        GildedRose.updateQualityOnItem(item);
        assertThat(item.getQuality(), is(4));
        assertThat(item.getSellIn(), is(11));
    }

    @Test
    public void updateQualityOnBackstagePassesAtMaxValue(){
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 2, 50);
        GildedRose.updateQualityOnItem(item);
        assertThat(item.getQuality(), is(50));
        assertThat(item.getSellIn(), is(1));
    }

    @Test
    public void updateQualityOnBackstagePassesAfterTheConcert(){
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 50);
        GildedRose.updateQualityOnItem(item);
        assertThat(item.getQuality(), is(0));
        assertThat(item.getSellIn(), is(-1));
    }

    @Test
    public void updateQualityOnBackstagePasses10DaysBefore(){
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10);
        GildedRose.updateQualityOnItem(item);
        assertThat(item.getQuality(), is(12));
        assertThat(item.getSellIn(), is(9));
    }

    @Test
    public void updateQualityOnBackstagePasses3DaysBefore(){
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 3, 10);
        GildedRose.updateQualityOnItem(item);
        assertThat(item.getQuality(), is(13));
        assertThat(item.getSellIn(), is(2));
    }

    @Test
    public void updateQualityOnConjured(){
        Item item = new Item("Conjured", 3, 10);
        GildedRose.updateQualityOnItem(item);
        assertThat(item.getQuality(), is(8));
        assertThat(item.getSellIn(), is(2));
    }

    @Test
    public void updateQualityOnConjuredExpired(){
        Item item = new Item("Conjured", 0, 10);
        GildedRose.updateQualityOnItem(item);
        assertThat(item.getQuality(), is(8));
        assertThat(item.getSellIn(), is(-1));
    }



}
