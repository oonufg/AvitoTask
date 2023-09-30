package ru.Pavel.Services;

import ru.Pavel.Domain.Persistance.Repositories.Tables.UserSegmentTable;

import java.util.Calendar;

public class TTLService {
    private static UserSegmentTable usTable;

    static {
        usTable = new UserSegmentTable();
    }

    public void deleteExpiredSegments(){
        long currentTimestamp = Calendar.getInstance().getTimeInMillis();
        usTable.deleteExpiredUserSegments(currentTimestamp);
    }
}
