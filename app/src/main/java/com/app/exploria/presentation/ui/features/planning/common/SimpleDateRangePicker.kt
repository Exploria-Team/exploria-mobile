import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SimpleDateRangePicker(
    showRangeModal: MutableState<Boolean>,
    selectedDateRange: MutableState<Pair<Long?, Long?>>,
    totalDays: MutableState<Int>
) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Pilih Rentang Tanggal:")

        Button(onClick = { showRangeModal.value = true }) {
            Text("Pilih Rentang Tanggal")
        }

        if (selectedDateRange.value.first != null && selectedDateRange.value.second != null) {
            val startDate = Date(selectedDateRange.value.first!!)
            val endDate = Date(selectedDateRange.value.second!!)
            val formattedStartDate = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(startDate)
            val formattedEndDate = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(endDate)

            totalDays.value =
                ((selectedDateRange.value.second!! - selectedDateRange.value.first!!) / (1000 * 60 * 60 * 24)).toInt() + 1

            Text(
                text = "Rentang Tanggal Terpilih:\n$formattedStartDate - $formattedEndDate",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Jumlah Hari: ${totalDays.value}",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            Text(
                text = "Belum Ada Rentang Tanggal yang Dipilih",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    if (showRangeModal.value) {
        DateRangePickerModal(
            onDateRangeSelected = {
                selectedDateRange.value = it
                showRangeModal.value = false
            },
            onDismiss = { showRangeModal.value = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerModal(
    onDateRangeSelected: (Pair<Long?, Long?>) -> Unit,
    onDismiss: () -> Unit
) {
    val dateRangePickerState = rememberDateRangePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onDateRangeSelected(
                        Pair(
                            dateRangePickerState.selectedStartDateMillis,
                            dateRangePickerState.selectedEndDateMillis
                        )
                    )
                    onDismiss()
                }
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Batal")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
        ) {
            Text(
                text = "Pilih Rentang Tanggal",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            DateRangePicker(
                state = dateRangePickerState,
                showModeToggle = false,
                modifier = Modifier
                    .width(400.dp)
                    .wrapContentHeight()
            )
        }
    }
}
