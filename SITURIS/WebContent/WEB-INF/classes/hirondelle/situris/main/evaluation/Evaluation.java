package hirondelle.situris.main.evaluation;

import static hirondelle.web4j.util.Consts.FAILS;

import hirondelle.situris.pub.register.Register;
import hirondelle.web4j.model.Check;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelUtil;
import hirondelle.web4j.security.SafeText;

public class Evaluation {

    public Evaluation(Integer aStars, SafeText aComment, Id aId)
            throws ModelCtorException {
        fStars = aStars;
        fComment = aComment;
        fId = aId;
        validateState();
    }

    public Integer getStars() {
        return fStars;
    }

    public SafeText getComment() {
        return fComment;
    }

    public Id getId() {
        return fId;
    }

    /** Intended for debugging only. Passwords are not emitted. */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(Register.class);
        builder.append(" Stars: " + fStars + " Comment: " + fComment);
        return builder.toString();
    }

    @Override
    public boolean equals(Object aThat) {
        Boolean result = ModelUtil.quickEquals(this, aThat);
        if (result == null) {
            Evaluation that = (Evaluation) aThat;
            result = ModelUtil.equalsFor(this.getSignificantFields(),
                    that.getSignificantFields());
        }
        return result;
    }

    @Override
    public int hashCode() {
        if (fHashCode == 0) {
            fHashCode = ModelUtil.hashCodeFor(getSignificantFields());
        }
        return fHashCode;
    }

    // PRIVATE //
    private final Integer fStars;
    private final SafeText fComment;
    private final Id fId;
    private int fHashCode;

    private void validateState() throws ModelCtorException {
        ModelCtorException ex = new ModelCtorException();

        if (FAILS == Check.optional(fComment, Check.range(5, 500))) {
            ex.add("Comment is optional but must be at least 5 characters to 500.");
        }
        /*
         * if ( FAILS == Check.) ) {
         * ex.add("Stars is required, between 1 and 5."); }
         */

        if (!ex.isEmpty())
            throw ex;
    }

    private Object[] getSignificantFields() {
        return new Object[] { fStars, fComment };
    }
}
