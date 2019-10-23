package com.vbank.views;

import java.sql.SQLException;

public interface View {
    View process() throws SQLException;
}