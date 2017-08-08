package org.heigit.bigspatialdata.oshdb.osm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.heigit.bigspatialdata.oshdb.OSHDB_H2;
import org.heigit.bigspatialdata.oshdb.osh.OSHNode;
import static org.heigit.bigspatialdata.oshdb.osh.OSHNodeTest.LONLAT_A;
import static org.heigit.bigspatialdata.oshdb.osh.OSHNodeTest.TAGS_A;
import static org.heigit.bigspatialdata.oshdb.osh.OSHNodeTest.USER_A;
import org.heigit.bigspatialdata.oshdb.util.OSMType;
import org.heigit.bigspatialdata.oshdb.util.TagTranslator;
import org.heigit.bigspatialdata.oshdb.util.tagInterpreter.TagInterpreter;
import org.junit.Assert;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Moritz Schott <m.schott@stud.uni-heidelberg.de>
 */
public class OSMWayTest {

  private static final Logger LOG = Logger.getLogger(OSMWayTest.class.getName());

  public OSMWayTest() {
  }

  @Test
  public void testGetRefs() {
    System.out.println("getRefs");
    OSMMember part = new OSMMember(1L, OSMType.NODE, 1);
    OSMWay instance = new OSMWay(1L, 1, 1L, 1L, 1, new int[]{}, new OSMMember[]{part, part});
    OSMMember[] expResult = new OSMMember[]{part, part};
    OSMMember[] result = instance.getRefs();
    assertArrayEquals(expResult, result);
  }

  @Test
  public void testGetRefsII() {
    System.out.println("getRefs");
    OSMWay instance = new OSMWay(1L, 1, 1L, 1L, 1, new int[]{}, new OSMMember[]{});
    OSMMember[] expResult = new OSMMember[]{};
    OSMMember[] result = instance.getRefs();
    assertArrayEquals(expResult, result);
  }

  @Test
  public void testGetRefsIII() {
    System.out.println("getRefs");
    OSMWay instance = new OSMWay(1L, 1, 1L, 1L, 1, new int[]{}, null);
    OSMMember[] expResult = null;
    OSMMember[] result = instance.getRefs();
    assertArrayEquals(expResult, result);
  }

  @Test
  public void testToString() {
    System.out.println("toString");
    OSMMember part = new OSMMember(1L, OSMType.NODE, 1);
    OSMWay instance = new OSMWay(1L, 1, 1L, 1L, 1, new int[]{}, new OSMMember[]{part, part});
    String expResult = "WAY-> ID:1 V:+1+ TS:1 CS:1 VIS:true UID:1 TAGS:[] Refs:[T:NODE ID:1 R:1, T:NODE ID:1 R:1]";
    String result = instance.toString();
    assertEquals(expResult, result);
  }

  @Test
  public void testToStringII() {
    System.out.println("toString");
    OSMWay instance = new OSMWay(1L, 1, 1L, 1L, 1, new int[]{1, 1, 2, 2}, new OSMMember[]{});
    String expResult = "WAY-> ID:1 V:+1+ TS:1 CS:1 VIS:true UID:1 TAGS:[1, 1, 2, 2] Refs:[]";
    String result = instance.toString();
    assertEquals(expResult, result);
  }

  @Test
  public void testToStringIII() {
    System.out.println("toString");
    OSMWay instance = new OSMWay(1L, 1, 1L, 1L, 1, new int[]{}, null);
    String expResult = "WAY-> ID:1 V:+1+ TS:1 CS:1 VIS:true UID:1 TAGS:[] Refs:null";
    String result = instance.toString();
    assertEquals(expResult, result);
  }

  @Test
  public void testToString_TagTranslator() throws SQLException, ClassNotFoundException {
    int[] properties = {1, 2};
    OSMMember[] refs = {new OSMMember(2L, OSMType.NODE, 2), new OSMMember(5L, OSMType.NODE, 2)};
    OSMWay instance = new OSMWay(1L, 1, 1L, 1L, 46, properties, refs);
    String expResult = "WAY-> ID:1 V:+1+ TS:1 CS:1 VIS:true UID:46 UName:FrankM TAGS:[(highway,footway)] Refs:[2,5]";
    String result = instance.toString(new TagTranslator(new OSHDB_H2("./src/test/resources/heidelberg-ccbysa").getConnection()));
    assertEquals(expResult, result);
  }

  @Test
  public void testCompareTo() {
    System.out.println("compareTo");
    OSMMember part = new OSMMember(1L, OSMType.NODE, 1);
    OSMWay o = new OSMWay(1L, 1, 1L, 1L, 1, new int[]{}, new OSMMember[]{part});
    OSMWay instance = new OSMWay(1L, 1, 1L, 1L, 1, new int[]{}, new OSMMember[]{part});

    assertTrue(instance.compareTo(o) == 0);
  }

  @Test
  public void testCompareToII() {
    System.out.println("compareTo");
    OSMMember part = new OSMMember(1L, OSMType.NODE, 1);
    OSMWay o = new OSMWay(1L, 3, 1L, 1L, 1, new int[]{}, new OSMMember[]{part});
    OSMWay instance = new OSMWay(1L, 1, 1L, 1L, 1, new int[]{}, new OSMMember[]{part});

    assertTrue(instance.compareTo(o) < 0);
  }

  @Test
  public void testCompareToIII() {
    System.out.println("compareTo");
    OSMMember part = new OSMMember(1L, OSMType.NODE, 1);
    OSMWay o = new OSMWay(1L, 1, 1L, 1L, 1, new int[]{}, new OSMMember[]{part});
    OSMWay instance = new OSMWay(1L, 3, 1L, 1L, 1, new int[]{}, new OSMMember[]{part});

    assertTrue(instance.compareTo(o) > 0);
  }

  //---------------
  @Test
  public void testGetId() {
    System.out.println("getId");
    OSMMember part = new OSMMember(1L, OSMType.NODE, 1);
    OSMWay instance = new OSMWay(1L, 1, 1L, 1L, 1, new int[]{}, new OSMMember[]{part, part});
    long expResult = 1L;
    long result = instance.getId();
    assertEquals(expResult, result);
  }

  @Test
  public void testGetVersion() {
    System.out.println("getVersion");
    OSMMember part = new OSMMember(1L, OSMType.NODE, 1);
    OSMWay instance = new OSMWay(1L, 1, 1L, 1L, 1, new int[]{}, new OSMMember[]{part, part});
    int expResult = 1;
    int result = instance.getVersion();
    assertEquals(expResult, result);
  }

  @Test
  public void testGetTimestamp() {
    System.out.println("getTimestamp");
    OSMMember part = new OSMMember(1L, OSMType.NODE, 1);
    OSMWay instance = new OSMWay(1L, 1, 1L, 1L, 1, new int[]{}, new OSMMember[]{part, part});
    long expResult = 1L;
    long result = instance.getTimestamp();
    assertEquals(expResult, result);
  }

  @Test
  public void testGetChangeset() {
    System.out.println("getChangeset");
    OSMMember part = new OSMMember(1L, OSMType.NODE, 1);
    OSMWay instance = new OSMWay(1L, 1, 1L, 1L, 1, new int[]{}, new OSMMember[]{part, part});
    long expResult = 1L;
    long result = instance.getChangeset();
    assertEquals(expResult, result);
  }

  @Test
  public void testGetUserId() {
    System.out.println("getUserId");
    OSMMember part = new OSMMember(1L, OSMType.NODE, 1);
    OSMWay instance = new OSMWay(1L, 1, 1L, 1L, 1, new int[]{}, new OSMMember[]{part, part});
    int expResult = 1;
    int result = instance.getUserId();
    assertEquals(expResult, result);
  }

  @Test
  public void testisVisible() {
    System.out.println("isVisible");
    OSMMember part = new OSMMember(1L, OSMType.NODE, 1);
    OSMWay instance = new OSMWay(1L, 1, 1L, 1L, 1, new int[]{}, new OSMMember[]{part, part});
    boolean expResult = true;
    boolean result = instance.isVisible();
    assertEquals(expResult, result);
  }

  @Test
  public void testisVisibleII() {
    System.out.println("isVisible");
    OSMMember part = new OSMMember(1L, OSMType.NODE, 1);
    OSMWay instance = new OSMWay(1L, -1, 1L, 1L, 1, new int[]{}, new OSMMember[]{part, part});
    boolean expResult = false;
    boolean result = instance.isVisible();
    assertEquals(expResult, result);
  }

  @Test
  public void testGetTags() {
    System.out.println("getTags");
    OSMMember part = new OSMMember(1L, OSMType.NODE, 1);
    OSMWay instance = new OSMWay(1L, 1, 1L, 1L, 1, new int[]{1, 1}, new OSMMember[]{part, part});
    int[] expResult = new int[]{1, 1};
    int[] result = instance.getTags();
    Assert.assertArrayEquals(expResult, result);
  }

  @Test
  public void testHasTagKey() {
    System.out.println("hasTagKey");
    OSMMember part = new OSMMember(1L, OSMType.NODE, 1);
    OSMWay instance = new OSMWay(1L, 1, 1L, 1L, 1, new int[]{}, new OSMMember[]{part, part});
    boolean expResult = false;
    boolean result = instance.hasTagKey(1);
    assertEquals(expResult, result);
  }

  @Test
  public void testHasTagKeyII() {
    System.out.println("hasTagKey");
    OSMMember part = new OSMMember(1L, OSMType.NODE, 1);
    OSMWay instance = new OSMWay(1L, 1, 1L, 1L, 1, new int[]{1, 1, 2, 2, 3, 3}, new OSMMember[]{part, part});
    boolean expResult = true;
    boolean result = instance.hasTagKey(1);
    assertEquals(expResult, result);
  }

  @Test
  public void testHasTagKeyIII() {
    System.out.println("hasTagKey");
    OSMMember part = new OSMMember(1L, OSMType.NODE, 1);
    OSMWay instance = new OSMWay(1L, 1, 1L, 1L, 1, new int[]{1, 2, 2, 2, 3, 3}, new OSMMember[]{part, part});
    boolean expResult = false;
    boolean result = instance.hasTagKey(1, new int[]{2, 3});
    assertEquals(expResult, result);
  }

  @Test
  public void testHasTagKeyIV() {
    System.out.println("hasTagKey");
    OSMMember part = new OSMMember(1L, OSMType.NODE, 1);
    OSMWay instance = new OSMWay(1L, 1, 1L, 1L, 1, new int[]{1, 1, 2, 2, 3, 3}, new OSMMember[]{part, part});
    boolean expResult = true;
    boolean result = instance.hasTagKey(1, new int[]{2, 3});
    assertEquals(expResult, result);
  }

  @Test
  public void testHasTagKeyV() {
    System.out.println("hasTagKey");
    OSMMember part = new OSMMember(1L, OSMType.NODE, 1);
    OSMWay instance = new OSMWay(1L, 1, 1L, 1L, 1, new int[]{2, 1, 3, 3}, new OSMMember[]{part, part});
    boolean expResult = false;
    boolean result = instance.hasTagKey(1, new int[]{1, 3});
    assertEquals(expResult, result);
  }

  @Test
  public void testHasTagValue() {
    System.out.println("hasTagValue");
    OSMMember part = new OSMMember(1L, OSMType.NODE, 1);
    OSMWay instance = new OSMWay(1L, 1, 1L, 1L, 1, new int[]{1, 2, 2, 3}, new OSMMember[]{part, part});
    boolean expResult = false;
    boolean result = instance.hasTagValue(1, 1);
    assertEquals(expResult, result);
  }

  @Test
  public void testHasTagValueII() {
    System.out.println("hasTagValue");
    OSMMember part = new OSMMember(1L, OSMType.NODE, 1);
    OSMWay instance = new OSMWay(1L, 1, 1L, 1L, 1, new int[]{1, 1, 2, 3}, new OSMMember[]{part, part});
    boolean expResult = true;
    boolean result = instance.hasTagValue(1, 1);
    assertEquals(expResult, result);
  }

  @Test
  public void testToGeoJSON_long_TagTranslator_TagInterpreter() throws SQLException, ClassNotFoundException, IOException {
    List<OSMNode> versions = new ArrayList<>();
    versions.add(new OSMNode(123l, 1, 1l, 0l, USER_A, TAGS_A, LONLAT_A[0], LONLAT_A[1]));
    OSHNode hnode = OSHNode.build(versions);
    OSMMember part = new OSMMember(1L, OSMType.NODE, 1, hnode);
    OSMWay instance = new OSMWay(1L, 1, 1L, 1L, 46, new int[]{1, 2}, new OSMMember[]{part,part});
    TagTranslator tt = new TagTranslator(new OSHDB_H2("./src/test/resources/heidelberg-ccbysa").getConnection());
    String expResult = "{\"type\":\"Feature\",\"id\":1,\"properties\":{\"visible\":true,\"version\":1,\"changeset\":1,\"timestamp\":\"1970-01-01T01:00:00Z\",\"user\":\"FrankM\",\"uid\":46,\"highway\":\"footway\"},\"geometry\":{\"type\":\"LineString\",\"coordinates\":[[8.675635,49.418620999999995],[8.675635,49.418620999999995]]},\"refs\":[1,1]}";
    
    String result = instance.toGeoJSON(1L, tt, new TagInterpreter(1, 1, null, null, null, 1, 1, 1));
    System.out.println(result);
    assertEquals(expResult, result);
  }
  
    @Test
  public void testToGeoJSON_long_TagTranslator_TagInterpreter_Exception() throws SQLException, ClassNotFoundException, IOException {
    OSMMember part = new OSMMember(1L, OSMType.NODE, 1);
    OSMWay instance = new OSMWay(1L, 1, 1L, 1L, 46, new int[]{1, 2}, new OSMMember[]{part,part});
    TagTranslator tt = new TagTranslator(new OSHDB_H2("./src/test/resources/heidelberg-ccbysa").getConnection());
    String expResult = "{\"type\":\"Feature\",\"id\":1,\"properties\":{\"visible\":true,\"version\":1,\"changeset\":1,\"timestamp\":\"1970-01-01T01:00:00Z\",\"user\":\"FrankM\",\"uid\":46,\"highway\":\"footway\"},\"refs\":[1,1]}";
    
    String result = instance.toGeoJSON(1L, tt, new TagInterpreter(1, 1, null, null, null, 1, 1, 1));
    assertEquals(expResult, result);
  }

}
