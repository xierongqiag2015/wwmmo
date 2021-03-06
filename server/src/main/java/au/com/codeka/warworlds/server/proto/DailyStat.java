// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: au/com/codeka/warworlds/server/proto/stats.proto at 14:1
package au.com.codeka.warworlds.server.proto;

import com.squareup.wire.FieldEncoding;
import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;
import java.io.IOException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import okio.ByteString;

public final class DailyStat extends Message<DailyStat, DailyStat.Builder> {
  public static final ProtoAdapter<DailyStat> ADAPTER = new ProtoAdapter_DailyStat();

  private static final long serialVersionUID = 0L;

  public static final Integer DEFAULT_DAY = 0;

  public static final Integer DEFAULT_ONEDA = 0;

  public static final Integer DEFAULT_SEVENDA = 0;

  public static final Integer DEFAULT_SIGNUPS = 0;

  @WireField(
      tag = 1,
      adapter = "com.squareup.wire.ProtoAdapter#INT32"
  )
  public final Integer day;

  @WireField(
      tag = 2,
      adapter = "com.squareup.wire.ProtoAdapter#INT32"
  )
  public final Integer oneda;

  @WireField(
      tag = 3,
      adapter = "com.squareup.wire.ProtoAdapter#INT32"
  )
  public final Integer sevenda;

  @WireField(
      tag = 4,
      adapter = "com.squareup.wire.ProtoAdapter#INT32"
  )
  public final Integer signups;

  public DailyStat(Integer day, Integer oneda, Integer sevenda, Integer signups) {
    this(day, oneda, sevenda, signups, ByteString.EMPTY);
  }

  public DailyStat(Integer day, Integer oneda, Integer sevenda, Integer signups, ByteString unknownFields) {
    super(ADAPTER, unknownFields);
    this.day = day;
    this.oneda = oneda;
    this.sevenda = sevenda;
    this.signups = signups;
  }

  @Override
  public Builder newBuilder() {
    Builder builder = new Builder();
    builder.day = day;
    builder.oneda = oneda;
    builder.sevenda = sevenda;
    builder.signups = signups;
    builder.addUnknownFields(unknownFields());
    return builder;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof DailyStat)) return false;
    DailyStat o = (DailyStat) other;
    return Internal.equals(unknownFields(), o.unknownFields())
        && Internal.equals(day, o.day)
        && Internal.equals(oneda, o.oneda)
        && Internal.equals(sevenda, o.sevenda)
        && Internal.equals(signups, o.signups);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode;
    if (result == 0) {
      result = unknownFields().hashCode();
      result = result * 37 + (day != null ? day.hashCode() : 0);
      result = result * 37 + (oneda != null ? oneda.hashCode() : 0);
      result = result * 37 + (sevenda != null ? sevenda.hashCode() : 0);
      result = result * 37 + (signups != null ? signups.hashCode() : 0);
      super.hashCode = result;
    }
    return result;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    if (day != null) builder.append(", day=").append(day);
    if (oneda != null) builder.append(", oneda=").append(oneda);
    if (sevenda != null) builder.append(", sevenda=").append(sevenda);
    if (signups != null) builder.append(", signups=").append(signups);
    return builder.replace(0, 2, "DailyStat{").append('}').toString();
  }

  public static final class Builder extends Message.Builder<DailyStat, Builder> {
    public Integer day;

    public Integer oneda;

    public Integer sevenda;

    public Integer signups;

    public Builder() {
    }

    public Builder day(Integer day) {
      this.day = day;
      return this;
    }

    public Builder oneda(Integer oneda) {
      this.oneda = oneda;
      return this;
    }

    public Builder sevenda(Integer sevenda) {
      this.sevenda = sevenda;
      return this;
    }

    public Builder signups(Integer signups) {
      this.signups = signups;
      return this;
    }

    @Override
    public DailyStat build() {
      return new DailyStat(day, oneda, sevenda, signups, buildUnknownFields());
    }
  }

  private static final class ProtoAdapter_DailyStat extends ProtoAdapter<DailyStat> {
    ProtoAdapter_DailyStat() {
      super(FieldEncoding.LENGTH_DELIMITED, DailyStat.class);
    }

    @Override
    public int encodedSize(DailyStat value) {
      return (value.day != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, value.day) : 0)
          + (value.oneda != null ? ProtoAdapter.INT32.encodedSizeWithTag(2, value.oneda) : 0)
          + (value.sevenda != null ? ProtoAdapter.INT32.encodedSizeWithTag(3, value.sevenda) : 0)
          + (value.signups != null ? ProtoAdapter.INT32.encodedSizeWithTag(4, value.signups) : 0)
          + value.unknownFields().size();
    }

    @Override
    public void encode(ProtoWriter writer, DailyStat value) throws IOException {
      if (value.day != null) ProtoAdapter.INT32.encodeWithTag(writer, 1, value.day);
      if (value.oneda != null) ProtoAdapter.INT32.encodeWithTag(writer, 2, value.oneda);
      if (value.sevenda != null) ProtoAdapter.INT32.encodeWithTag(writer, 3, value.sevenda);
      if (value.signups != null) ProtoAdapter.INT32.encodeWithTag(writer, 4, value.signups);
      writer.writeBytes(value.unknownFields());
    }

    @Override
    public DailyStat decode(ProtoReader reader) throws IOException {
      Builder builder = new Builder();
      long token = reader.beginMessage();
      for (int tag; (tag = reader.nextTag()) != -1;) {
        switch (tag) {
          case 1: builder.day(ProtoAdapter.INT32.decode(reader)); break;
          case 2: builder.oneda(ProtoAdapter.INT32.decode(reader)); break;
          case 3: builder.sevenda(ProtoAdapter.INT32.decode(reader)); break;
          case 4: builder.signups(ProtoAdapter.INT32.decode(reader)); break;
          default: {
            FieldEncoding fieldEncoding = reader.peekFieldEncoding();
            Object value = fieldEncoding.rawProtoAdapter().decode(reader);
            builder.addUnknownField(tag, fieldEncoding, value);
          }
        }
      }
      reader.endMessage(token);
      return builder.build();
    }

    @Override
    public DailyStat redact(DailyStat value) {
      Builder builder = value.newBuilder();
      builder.clearUnknownFields();
      return builder.build();
    }
  }
}
