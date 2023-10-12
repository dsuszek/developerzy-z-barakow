package org.kainos.ea.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.exception.FailedToGetRoleException;
import org.kainos.ea.exception.FailedToGetRolesException;
import org.kainos.ea.exception.RoleDoesNotExistException;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.model.JobRoleDetails;
import org.kainos.ea.service.JobRoleService;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class JobServiceTest {

    JobRoleDao jobDaoMock = mock(JobRoleDao.class);
    DatabaseConnector databaseConnectorMock = mock(DatabaseConnector.class);
    Connection connMock = mock ( Connection.class);

    Connection conn;

    JobRoleDetails jobRole1 = new JobRoleDetails((short) 1, "Tester", "good", "www.onet.pl", "test", (short) 1, "test");
    JobRole jobRole2 = new JobRole( "Tester", "good", "www.onet.pl");
    JobRole jobRole3 = new JobRole( "Tester2", "good", "www.onet.pl");
    JobRoleService jobService = new JobRoleService(jobDaoMock, databaseConnectorMock);


    @Test
    void getJobRoles_whenJobRolesAreUnavailable_shouldReturnEmptyList() throws SQLException {
        List<JobRole> testList = new ArrayList<JobRole>();
        when(jobDaoMock.getRoles(connMock)).thenReturn(testList);
        Assertions.assertTrue(testList.isEmpty());
    }

    @Test
    void getJobRoleById_whenJobRoleExists_expectJobRoleToBeReturned() throws RoleDoesNotExistException, FailedToGetRoleException, SQLException {
        //given
        when(jobDaoMock.findRoleById(1, databaseConnectorMock.getConnection())).thenReturn(Optional.ofNullable(jobRole1));
        //when
        JobRoleDetails jobRole = jobService.findRoleById(1);
        //then
        assertThat(jobRole).isEqualTo(jobRole1);
    }

    @Test
    void getRoleById_When_NoRoleReturned_RoleDoesNotExistExceptionToBeThrown() throws SQLException, FailedToGetRoleException {
        when(jobDaoMock.findRoleById(20, connMock)).thenReturn(Optional.empty());
        assertThatExceptionOfType(RoleDoesNotExistException.class)
                .isThrownBy(() -> jobService.findRoleById(20));
    }


}