import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emp_name")
data class EmployeeDBModel(
        @PrimaryKey(autoGenerate = true) val empId: Int?,
        @ColumnInfo(name = "empFirstName") val empFirstName: String,
        @ColumnInfo(name = "empLastName") val empLastName: String,
        @ColumnInfo(name = "empDob") val empDob: String,
        @ColumnInfo(name = "empType") val empType: String
)
