package com.sds.icto.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	void execute(
		HttpServletRequest request, 
		HttpServletResponse response)
				throws SQLException, ClassNotFoundException,
					   ServletException, IOException;
}
