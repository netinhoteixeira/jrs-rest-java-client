/*
 * Copyright (C) 2005 - 2014 Jaspersoft Corporation. All rights  reserved.
 * http://www.jaspersoft.com.
 *
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License  as
 * published by the Free Software Foundation, either version 3 of  the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero  General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public  License
 * along with this program.&nbsp; If not, see <http://www.gnu.org/licenses/>.
 */

package com.jaspersoft.jasperserver.jaxrs.client.apiadapters.reporting;

import com.jaspersoft.jasperserver.jaxrs.client.apiadapters.AbstractAdapter;
import com.jaspersoft.jasperserver.jaxrs.client.apiadapters.CommonExceptionHandler;
import com.jaspersoft.jasperserver.jaxrs.client.core.SessionStorage;
import com.jaspersoft.jasperserver.jaxrs.client.core.operationresult.OperationResult;
import com.jaspersoft.jasperserver.jaxrs.client.dto.reports.ReportExecutionDescriptor;
import com.jaspersoft.jasperserver.jaxrs.client.dto.reports.ReportExecutionRequest;

import static com.jaspersoft.jasperserver.jaxrs.client.core.JerseyRequestBuilder.buildRequest;

public class ReportingService extends AbstractAdapter {

    public ReportingService(SessionStorage sessionStorage) {
        super(sessionStorage);
    }

    public OperationResult<ReportExecutionDescriptor> newReportExecutionRequest(ReportExecutionRequest request) {
        OperationResult<ReportExecutionDescriptor> descriptor =
                buildRequest(sessionStorage, ReportExecutionDescriptor.class, new String[]{"/reportExecutions"}, new CommonExceptionHandler())
                        .post(request);
        sessionStorage.setSessionId(descriptor.getSessionId());
        return descriptor;
    }

    public ReportExecutionRequestBuilder reportExecutionRequest(String requestId) {
        return new ReportExecutionRequestBuilder(sessionStorage, requestId);
    }

    public ReportsAndJobsSearchAdapter runningReportsAndJobs() {
        return new ReportsAndJobsSearchAdapter(sessionStorage);
    }

    public ReportsAdapter report(String reportUnitUri) {
        return new ReportsAdapter(sessionStorage, reportUnitUri);
    }

}
