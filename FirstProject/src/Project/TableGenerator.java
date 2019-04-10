package Project;

public @interface TableGenerator {

	String pkColumnName();

	String valueColumnName();

	String pkColumnValue();

	String name();

	String table();

}
