package services.creates.create;

import services.creates.acreate.SqliteColumn;
import services.creates.acreate.additiveColumnConstraint;
import java.lang.reflect.Field;

/**
 * convert model object fields to database column, collect metadata*/
 class CollectorMember {

   final static StringBuilder filedType = new StringBuilder();
   final static StringBuilder constraint = new StringBuilder();

    protected static StringBuilder getMemberString(String dataType, Field _objectAttributes)  {
        /* set table column type and other attributes depend on data type of class member */

        switch (dataType) {
            case "String":
                /* check for column is existed*/
                if (_objectAttributes.getAnnotation(SqliteColumn.class) == null) {
                    throw new NullPointerException();
                }

                /* reset the values to ensure no conflict has been occurs  */
                filedType.setLength(0);
                constraint.setLength(0);
                /* check the default of 'check attribute' value of annotation was changed to put in the sql query */
                if (!(_objectAttributes.getAnnotation(SqliteColumn.class).check().contentEquals("SQL_Condition"))) {
                    constraint.append("CHECK" + "(").append(_objectAttributes.getAnnotation(SqliteColumn.class).check()).append(") ");
                }
                /* check the default of 'DEFAULT attribute' value of annotation was changed to put in the sql query */
                if (_objectAttributes.getAnnotation(SqliteColumn.class).defaultConstraint() != 0.000) {
                    constraint.append("DEFAULT ").append(_objectAttributes.getAnnotation(SqliteColumn.class).defaultConstraint());
                }
                /* check 'additiveColumnConstraint' was used to put it value in the sql query */
                if( _objectAttributes.getAnnotation(additiveColumnConstraint.class) != null ) {
                    constraint.append(" ").append(_objectAttributes.getAnnotation(additiveColumnConstraint.class).constraint().displayName());
                }

                constraint.append(' ').append(_objectAttributes.getAnnotation(SqliteColumn.class).constraint().displayName());

               return filedType.append("text").append(' ').append(constraint);

            case "int":

                if (_objectAttributes.getAnnotation(SqliteColumn.class) == null) {
                    throw new NullPointerException();
                }
                filedType.setLength(0);
                constraint.setLength(0);
                if (!_objectAttributes.getAnnotation(SqliteColumn.class).check()
                        .contentEquals("SQL_Condition")) {
                    constraint.append("CHECK").append("(").append(
                            _objectAttributes.getAnnotation(SqliteColumn.class).check()).append(") ");
                }
                if (_objectAttributes.getAnnotation(SqliteColumn.class).defaultConstraint() != 0.000) {
                    constraint.append("DEFAULT ").append(_objectAttributes.getAnnotation(SqliteColumn.class).defaultConstraint());
                }
                if( _objectAttributes.getAnnotation(additiveColumnConstraint.class) != null ) {
                    constraint.append(" ").append(_objectAttributes.getAnnotation (additiveColumnConstraint.class).constraint().displayName());
                }

                constraint.append(' ').append(_objectAttributes.getAnnotation(SqliteColumn.class).constraint().displayName());
                return filedType.append("integer").append(' ').append(constraint);


            case "Long":

                if (_objectAttributes.getAnnotation(SqliteColumn.class) == null) {
                    throw new NullPointerException();
                }
                filedType.setLength(0);
                constraint.setLength(0);
                if (!_objectAttributes.getAnnotation(SqliteColumn.class).check()
                        .contentEquals("SQL_Condition")) {
                    constraint.append("CHECK" + "(").append(_objectAttributes.getAnnotation(SqliteColumn.class).check()).append(") ");
                }
                if (_objectAttributes.getAnnotation(SqliteColumn.class).defaultConstraint() != 0.000) {
                    constraint.append("DEFAULT ").append(_objectAttributes.getAnnotation(SqliteColumn.class).defaultConstraint());
                }
                if( _objectAttributes.getAnnotation(additiveColumnConstraint.class) != null ) {
                    constraint.append(" ").append(_objectAttributes.getAnnotation (additiveColumnConstraint.class).constraint().displayName());
                }
                constraint.append(' ').append(_objectAttributes.getAnnotation(SqliteColumn.class).constraint().displayName());
                return filedType.append("BIGINT").append(' ').append(constraint);

            case "Byte":
                if (_objectAttributes.getAnnotation(SqliteColumn.class) == null) {
                    throw new NullPointerException();
                }
                filedType.setLength(0);
                constraint.setLength(0);
                if (!_objectAttributes.getAnnotation(SqliteColumn.class).check()
                        .contentEquals("SQL_Condition")) {
                    constraint.append("CHECK" + "(").append(_objectAttributes.getAnnotation(SqliteColumn.class).check()).append(") ");
                }
                if (_objectAttributes.getAnnotation(SqliteColumn.class).defaultConstraint() != 0.000) {
                    constraint.append("DEFAULT ").append(_objectAttributes.getAnnotation(SqliteColumn.class).defaultConstraint());
                }

                if( _objectAttributes.getAnnotation(additiveColumnConstraint.class) != null ) {
                    constraint.append(" ").append(_objectAttributes.getAnnotation (additiveColumnConstraint.class).constraint().displayName());
                }
                constraint.append(' ').append(_objectAttributes.getAnnotation(SqliteColumn.class).constraint().displayName());

                return filedType.append("BIT" + ' ').append(constraint);


            case "float":
                if (_objectAttributes.getAnnotation(SqliteColumn.class) == null) {
                    throw new NullPointerException();

                }
                filedType.setLength(0);
                constraint.setLength(0);
                if (!_objectAttributes.getAnnotation(SqliteColumn.class).check()
                        .contentEquals("SQL_Condition")) {
                    constraint.append("CHECK" + "(").append(_objectAttributes.getAnnotation(SqliteColumn.class).check()).append(") ");
                }
                if (_objectAttributes.getAnnotation(SqliteColumn.class).defaultConstraint() != 0.000) {
                    constraint.append("DEFAULT ").append(_objectAttributes.getAnnotation(SqliteColumn.class).defaultConstraint());
                }

                if( _objectAttributes.getAnnotation(additiveColumnConstraint.class) != null ) {
                    constraint.append(" ").append(_objectAttributes.getAnnotation (additiveColumnConstraint.class).constraint().displayName());
                }
                constraint.append(' ').append(_objectAttributes.getAnnotation(SqliteColumn.class).constraint().displayName());


                return   filedType.append("FLOAT").append(' ').append(constraint);

            case "double":

                if (_objectAttributes.getAnnotation(SqliteColumn.class) == null) {
                    throw new NullPointerException();
                }
                filedType.setLength(0);
                constraint.setLength(0);
                if (!_objectAttributes.getAnnotation(SqliteColumn.class).check()
                        .contentEquals("SQL_Condition")) {
                    constraint.append("CHECK" + "(").append(_objectAttributes.getAnnotation(SqliteColumn.class).check()).append(") ");
                }
                if (_objectAttributes.getAnnotation(SqliteColumn.class).defaultConstraint() != 0.000) {
                    constraint.append("DEFAULT ").append(_objectAttributes.getAnnotation(SqliteColumn.class).defaultConstraint());
                }
                if( _objectAttributes.getAnnotation(additiveColumnConstraint.class) != null ) {
                    constraint.append(" ").append(_objectAttributes.getAnnotation (additiveColumnConstraint.class).constraint().displayName());
                }
                constraint.append(' ').append(_objectAttributes.getAnnotation(SqliteColumn.class).constraint().displayName());

                return  filedType.append("DOUBLE").append(' ').append(constraint);

            case "boolean":

                if (_objectAttributes.getAnnotation(SqliteColumn.class) == null) {
                    throw new NullPointerException();
                }
                filedType.setLength(0);
                constraint.setLength(0);
                if (!_objectAttributes.getAnnotation(SqliteColumn.class).check()
                        .contentEquals("SQL_Condition")) {
                    constraint.append("CHECK" + "(").append(_objectAttributes.getAnnotation(SqliteColumn.class).check()).append(") ");
                }
                if (_objectAttributes.getAnnotation(SqliteColumn.class).defaultConstraint() != 0.000) {
                    constraint.append("DEFAULT ").append(_objectAttributes.getAnnotation(SqliteColumn.class).defaultConstraint());
                }
                if( _objectAttributes.getAnnotation(additiveColumnConstraint.class) != null ) {
                    constraint.append(" ").append(_objectAttributes.getAnnotation (additiveColumnConstraint.class).constraint().displayName());
                }
                constraint.append(' ').append(_objectAttributes.getAnnotation(SqliteColumn.class).constraint().displayName());

                return filedType.append("BOOLEAN" + ' ').append(constraint);


            case "File":

                if (_objectAttributes.getAnnotation(SqliteColumn.class) == null) {
                    throw new NullPointerException();
                }
                filedType.setLength(0);
                constraint.setLength(0);
                if (!_objectAttributes.getAnnotation(SqliteColumn.class).check()
                        .contentEquals("SQL_Condition")) {
                    constraint.append("CHECK" + "(").append(_objectAttributes.getAnnotation(SqliteColumn.class).check()).append(") ");
                }
                if (_objectAttributes.getAnnotation(SqliteColumn.class).defaultConstraint() != 0.000) {
                    constraint.append("DEFAULT ").append(_objectAttributes.getAnnotation(SqliteColumn.class).defaultConstraint());
                }
                if( _objectAttributes.getAnnotation(additiveColumnConstraint.class) != null ) {
                    constraint.append(" ").append(_objectAttributes.getAnnotation (additiveColumnConstraint.class).constraint().displayName());
                }
                constraint.append(' ').append(_objectAttributes.getAnnotation(SqliteColumn.class).constraint().displayName());

                return   filedType.append("BLOB" + ' ').append(constraint).append(' ');

            default:
                return filedType.append("null");
        }
    }
}
