package hirondelle.situris.main.centrosInteresse;

import hirondelle.web4j.model.Check;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelUtil;
import static hirondelle.web4j.util.Consts.FAILS;
import hirondelle.web4j.security.SafeText;
import java.util.*;
import hirondelle.web4j.model.Validator;
import java.util.logging.*;
import hirondelle.web4j.util.Util;

/** Model Object for an Image. */
public final class Image {

  /**
    Full constructor.
    @param aDescription Description of the image, required, length in range 1..100.
    @param aFileName Name of uploaded image file, required, length in range 1..100, ends in .jpeg,
    .jpg, .gif, or .png.
    @param aFileContentType MIME type of the uploaded file, required, length in range 1..100, must be
    image/jpeg, image/gif, or image/png.
    @param aFileSize Size of the uploaded file, required, must be in range 1K to 50K.
   */
  public Image(SafeText aFileName, SafeText aFileContentType, Long aFileSize)
    throws ModelCtorException {
    fFileName = aFileName;
    fFileContentType = aFileContentType;
    fFileSize = aFileSize;
    validateState();
  }
  
  SafeText getFileName() {
    return fFileName;
  }

  SafeText getFileContentType() {
    return fFileContentType;
  }

  Long getFileSize() {
    return fFileSize;
  }

  @Override public String toString() {
    return ModelUtil.toStringFor(this);
  }

  @Override public boolean equals(Object aThat) {
    Boolean result = ModelUtil.quickEquals(this, aThat);
    if (result == null) {
      Image that = (Image)aThat;
      result = ModelUtil.equalsFor(this.getSignificantFields(), that.getSignificantFields());
    }
    return result;
  }

  @Override public int hashCode() {
    if (fHashCode == 0) {
      fHashCode = ModelUtil.hashCodeFor(getSignificantFields());
    }
    return fHashCode;
  }

  // PRIVATE //
  private final SafeText fFileName;
  private final SafeText fFileContentType;
  private final Long fFileSize;
  private int fHashCode;
  
  private static List<String> CONTENT_TYPES = Arrays.asList("image/gif", "image/jpeg", "image/png");
  private static List<String> EXTENSIONS = Arrays.asList("gif", "jpeg", "png", "jpg");
  private static Validator CHECK_EXTENSION = new ValidateExtension();
  private static Validator CHECK_CONTENT_TYPE = new ValidateContentType();
  private static final Logger fLogger = Util.getLogger(Image.class);

  private void validateState() throws ModelCtorException {
    ModelCtorException ex = new ModelCtorException();

    if(!fFileName.toString().isEmpty()){
      if (FAILS == Check.required(fFileName, Check.range(1, 100), CHECK_EXTENSION)) {
        ex.add("File Name is required. Must end in .jpeg, .jpg, .gif, or .png");
      }
      else {
        if (FAILS == Check.required(fFileContentType, Check.range(1, 100), CHECK_CONTENT_TYPE)) {
          ex.add("File Content-Type is required. Must be image/jpeg, image/gif, or image/png");
        }
        if (FAILS == Check.required(fFileSize, Check.range(512, 1024 * 500))) {
          ex.add("File size is required, in range 512b to 500K.");
        }
      }
    }

    if (!ex.isEmpty()) {
      fLogger.fine(ex.getMessage());
      throw ex;
    }
  }

  private Object[] getSignificantFields() {
    return new Object[]{fFileName, fFileContentType, fFileSize};
  }

  private static final class ValidateContentType implements Validator {
    public boolean isValid(Object aItem) {
      SafeText item = (SafeText)aItem;
      return CONTENT_TYPES.contains(item.getRawString());
    }
  }

  private static final class ValidateExtension implements Validator {
    public boolean isValid(Object aItem) {
      SafeText item = (SafeText)aItem;
      int lastPeriod = item.getRawString().lastIndexOf(".");
      String fileExtension = item.getRawString().substring(lastPeriod + 1);
      return EXTENSIONS.contains(fileExtension);
    }
  }
}
