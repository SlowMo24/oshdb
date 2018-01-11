package org.heigit.bigspatialdata.oshdb.osm;

import java.util.Arrays;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class OSMEntity {

  private static final Logger LOG = LoggerFactory.getLogger(OSMEntity.class);

  protected final long id;

  protected final int version;
  protected final long timestamp;
  protected final long changeset;
  protected final int userId;
  protected final int[] tags;

  /**
   * Constructor for a OSMEntity. Holds the basic information, every OSM-Object has.
   *
   * @param id ID
   * @param version Version. Versions &lt;=0 define visible Entities, &gt;0 deleted Entities.
   * @param timestamp Timestamp in seconds since 01.01.1970 00:00:00 UTC.
   * @param changeset Changeset-ID
   * @param userId UserID. This is also the link to the UserName in the OSH-Db (see
   *        {@link org.heigit.bigspatialdata.oshdb.util.TagTranslator#TagTranslator(java.sql.Connection)
   *        TagTranslator})
   * @param tags An array of OSH-Db key-value ids. The format is [KID1,VID1,KID2,VID2...KIDn,VIDn].
   *        They can be translated to String and back using the
   *        {@link org.heigit.bigspatialdata.oshdb.util.TagTranslator#TagTranslator(java.sql.Connection)
   *        TagTranslator}.
   */
  public OSMEntity(final long id, final int version, final long timestamp, final long changeset,
      final int userId, final int[] tags) {
    this.id = id;
    this.version = version;
    this.timestamp = timestamp;
    this.changeset = changeset;
    this.userId = userId;
    this.tags = tags;
  }

  public long getId() {
    return id;
  }

  public abstract OSMType getType();

  public int getVersion() {
    return Math.abs(version);
  }

  public long getTimestamp() {
    return timestamp;
  }

  public long getChangeset() {
    return changeset;
  }

  public int getUserId() {
    return userId;
  }

  public boolean isVisible() {
    return (version >= 0);
  }

  public int[] getTags() {
    return tags;
  }

  public boolean hasTagKey(int key) {
    for (int i = 0; i < tags.length; i += 2) {
      if (tags[i] < key) {
        continue;
      }
      if (tags[i] == key) {
        return true;
      }
      if (tags[i] > key) {
        return false;
      }
    }
    return false;
  }


  /**
   * Tests if any a given key is present but ignores certain values. Useful when looking for example
   * "TagKey" != "no"
   *
   * @param key the key to search for
   * @param uninterestingValues list of values, that should return false although the key is
   *        actually present
   * @return true if the key is present and is NOT in a combination with the given values, false
   *         otherwise
   */
  public boolean hasTagKeyExcluding(int key, int[] uninterestingValues) {
    for (int i = 0; i < tags.length; i += 2) {
      if (tags[i] < key) {
        continue;
      }
      if (tags[i] == key) {
        final int value = tags[i + 1];
        return !IntStream.of(uninterestingValues).anyMatch(x -> x == value);
      }
      if (tags[i] > key) {
        return false;
      }
    }
    return false;
  }

  public boolean hasTagValue(int key, int value) {
    for (int i = 0; i < tags.length; i += 2) {
      if (tags[i] < key) {
        continue;
      }
      if (tags[i] == key) {
        return tags[i + 1] == value;
      }
      if (tags[i] > key) {
        return false;
      }
    }
    return false;
  }



  public boolean equalsTo(OSMEntity o) {
    return id == o.id && version == o.version && timestamp == o.timestamp
        && changeset == o.changeset && userId == o.userId && Arrays.equals(tags, o.tags);
  }

  @Override
  public String toString() {
    return String.format("ID:%d V:+%d+ TS:%d CS:%d VIS:%s UID:%d TAGS:%S", getId(), getVersion(),
        getTimestamp(), getChangeset(), isVisible(), getUserId(), Arrays.toString(getTags()));
  }



}
