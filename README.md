# elements

As a Reviewer, I want a subject in the README.md explaining the chosen architecture.

Why I choose Model-View-ViewModel (MVVM) architecture is because.
- It separates the UI code from the business logic, making it easier to write and maintain.
- It can improve the performance of the app, it is possible to perform operations (such as database queries or network requests) on a background thread, without blocking the main UI thread.

As a Reviewer, I want the application to be offline-first. The data must be fetched from the API and must be stored in a database. If there is no internet the data from the database must be used.
This is how I would implement a SQLITE data base. Of course there are other databases as well lik Room and you can store in SharedPreferences

class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        // Database name and version
        private const val DATABASE_NAME = "mydatabase.db"
        private const val DATABASE_VERSION = 1
    }
    override fun onCreate(db: SQLiteDatabase) {
        // Define the SQL commands to create the tables and columns in the database
        val createTableSql = "CREATE TABLE $TABLE_NAME (_id INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NAME TEXT NOT NULL, $COLUMN_AGE INTEGER NOT NULL)"
        db.execSQL(createTableSql)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Define how to upgrade the database when the schema changes
        // For example, you can drop the old tables and recreate them
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}


