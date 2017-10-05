// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: google/protobuf/descriptor.proto at 549:1
package com.google.protobuf;

import com.squareup.wire.FieldEncoding;
import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;
import java.io.IOException;
import java.lang.Boolean;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.util.List;
import okio.ByteString;

public final class EnumValueOptions extends Message<EnumValueOptions, EnumValueOptions.Builder> {
  public static final ProtoAdapter<EnumValueOptions> ADAPTER = new ProtoAdapter_EnumValueOptions();

  private static final long serialVersionUID = 0L;

  public static final Boolean DEFAULT_DEPRECATED = false;

  /**
   * Is this enum value deprecated?
   * Depending on the target platform, this can emit Deprecated annotations
   * for the enum value, or it will be completely ignored; in the very least,
   * this is a formalization for deprecating enum values.
   */
  @WireField(
      tag = 1,
      adapter = "com.squareup.wire.ProtoAdapter#BOOL"
  )
  public final Boolean deprecated;

  /**
   * The parser stores options it doesn't recognize here. See above.
   */
  @WireField(
      tag = 999,
      adapter = "com.google.protobuf.UninterpretedOption#ADAPTER",
      label = WireField.Label.REPEATED
  )
  public final List<UninterpretedOption> uninterpreted_option;

  public EnumValueOptions(Boolean deprecated, List<UninterpretedOption> uninterpreted_option) {
    this(deprecated, uninterpreted_option, ByteString.EMPTY);
  }

  public EnumValueOptions(Boolean deprecated, List<UninterpretedOption> uninterpreted_option, ByteString unknownFields) {
    super(ADAPTER, unknownFields);
    this.deprecated = deprecated;
    this.uninterpreted_option = Internal.immutableCopyOf("uninterpreted_option", uninterpreted_option);
  }

  @Override
  public Builder newBuilder() {
    Builder builder = new Builder();
    builder.deprecated = deprecated;
    builder.uninterpreted_option = Internal.copyOf("uninterpreted_option", uninterpreted_option);
    builder.addUnknownFields(unknownFields());
    return builder;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof EnumValueOptions)) return false;
    EnumValueOptions o = (EnumValueOptions) other;
    return Internal.equals(unknownFields(), o.unknownFields())
        && Internal.equals(deprecated, o.deprecated)
        && Internal.equals(uninterpreted_option, o.uninterpreted_option);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode;
    if (result == 0) {
      result = unknownFields().hashCode();
      result = result * 37 + (deprecated != null ? deprecated.hashCode() : 0);
      result = result * 37 + (uninterpreted_option != null ? uninterpreted_option.hashCode() : 1);
      super.hashCode = result;
    }
    return result;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    if (deprecated != null) builder.append(", deprecated=").append(deprecated);
    if (uninterpreted_option != null) builder.append(", uninterpreted_option=").append(uninterpreted_option);
    return builder.replace(0, 2, "EnumValueOptions{").append('}').toString();
  }

  public static final class Builder extends Message.Builder<EnumValueOptions, Builder> {
    public Boolean deprecated;

    public List<UninterpretedOption> uninterpreted_option;

    public Builder() {
      uninterpreted_option = Internal.newMutableList();
    }

    /**
     * Is this enum value deprecated?
     * Depending on the target platform, this can emit Deprecated annotations
     * for the enum value, or it will be completely ignored; in the very least,
     * this is a formalization for deprecating enum values.
     */
    public Builder deprecated(Boolean deprecated) {
      this.deprecated = deprecated;
      return this;
    }

    /**
     * The parser stores options it doesn't recognize here. See above.
     */
    public Builder uninterpreted_option(List<UninterpretedOption> uninterpreted_option) {
      Internal.checkElementsNotNull(uninterpreted_option);
      this.uninterpreted_option = uninterpreted_option;
      return this;
    }

    @Override
    public EnumValueOptions build() {
      return new EnumValueOptions(deprecated, uninterpreted_option, buildUnknownFields());
    }
  }

  private static final class ProtoAdapter_EnumValueOptions extends ProtoAdapter<EnumValueOptions> {
    ProtoAdapter_EnumValueOptions() {
      super(FieldEncoding.LENGTH_DELIMITED, EnumValueOptions.class);
    }

    @Override
    public int encodedSize(EnumValueOptions value) {
      return (value.deprecated != null ? ProtoAdapter.BOOL.encodedSizeWithTag(1, value.deprecated) : 0)
          + UninterpretedOption.ADAPTER.asRepeated().encodedSizeWithTag(999, value.uninterpreted_option)
          + value.unknownFields().size();
    }

    @Override
    public void encode(ProtoWriter writer, EnumValueOptions value) throws IOException {
      if (value.deprecated != null) ProtoAdapter.BOOL.encodeWithTag(writer, 1, value.deprecated);
      if (value.uninterpreted_option != null) UninterpretedOption.ADAPTER.asRepeated().encodeWithTag(writer, 999, value.uninterpreted_option);
      writer.writeBytes(value.unknownFields());
    }

    @Override
    public EnumValueOptions decode(ProtoReader reader) throws IOException {
      Builder builder = new Builder();
      long token = reader.beginMessage();
      for (int tag; (tag = reader.nextTag()) != -1;) {
        switch (tag) {
          case 1: builder.deprecated(ProtoAdapter.BOOL.decode(reader)); break;
          case 999: builder.uninterpreted_option.add(UninterpretedOption.ADAPTER.decode(reader)); break;
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
    public EnumValueOptions redact(EnumValueOptions value) {
      Builder builder = value.newBuilder();
      Internal.redactElements(builder.uninterpreted_option, UninterpretedOption.ADAPTER);
      builder.clearUnknownFields();
      return builder.build();
    }
  }
}
