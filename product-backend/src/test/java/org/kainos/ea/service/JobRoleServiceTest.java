package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.db.JobRoleDao;
import org.kainos.ea.exception.FailedToCreateJobRoleException;
import org.kainos.ea.exception.InvalidJobRoleException;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.model.JobRoleRequest;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JobRoleServiceTest {
    private final JobRoleDao jobRoleDaoMock = mock(JobRoleDao.class);
    private final JobRoleValidator jobRoleValidatorMock = mock(JobRoleValidator.class);
    private JobRoleService jobRoleService = new JobRoleService(jobRoleDaoMock, jobRoleValidatorMock);
    private final JobRole MOCKED_JOB_ROLE = new JobRole(
            (short) 100,
            "Technology LeaderTechnology LeaderTechnology LeaderTechnology LeaderTechnology LeaderTechnology LeaderTechnology LeaderTechnology LeaderTechnology LeaderTechnology LeaderTechnology LeaderTechnology LeaderTechnology LeaderTechnology LeaderTechnology LeaderTechnology LeaderTechnology LeaderTechnology Leader",
            "A technology leader is key strategic role within the business making executive technology decisions on behalf of the business, based upon the sector and practices’ strategic direction and goals. The core responsibilities of a technology leader in Kainos include setting a Technology direction, a technical advisor to the business and C-level clients, maintaining a commercial edge over other technology services providers, developing and nurturing technical talent across the organisation and representing Kainos as a technology evangelist.",
            "https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20Profile%20%2D%20Technology%20Leader%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1",
            (short) 1
    );


    @Test
    void createJobRole_When_ThereIsValidationError_Expect_InvalidJobRoleExceptionToBeThrown() {
        // given
        JobRoleRequest mockJobRoleRequest = new JobRoleRequest(
                "Front-end EngineerFront-end EngineerFront-end EngineerFront-end EngineerFront-end EngineerFront-end EngineerFront-end EngineerFront-end EngineerFront-end EngineerFront-end EngineerFront-end EngineerFront-end EngineerFront-end EngineerFront-end EngineerFront-end Engineer",
                "As a Front-end Engineer in Kainos, you will have the opportunity to use your expertise in developing high quality user interface solutions which delight our customers and impact the lives of users worldwide.\n" +
                "The projects you will join are varied, and often highly visible. You will be working in fast- paced, agile environments, so it is important for you to make sound, reasoned decisions, and recommendations on front-end and user interfaces with your colleagues.\n" +
                "You are determined, flexible and always constructive; proactive in improving things and are always inclusive and respectful in your interactions with your team. You will be working alongside talented, diverse, enthusiastic colleagues, who will help you learn and develop as you, in turn, mentor those around you.",
                "https://kainossoftwareltd.sharepoint.com/sites/PeopleTeam-SharedDrive/Shared%20Documents/Forms/AllItems.aspx?id=%2Fsites%2FPeopleTeam%2DSharedDrive%2FShared%20Documents%2FPeople%20Team%20Shared%20Drive%2FOrganisational%20Development%20%26%20Learning%2FCareer%20Lattice%2FApproved%20Job%20Profiles%2FEngineering%2FEngineering%2FJob%20Profile%20%2D%20Front%2DEnd%20Engineer%20%28A%29%2Epdf&parent=%2Fsites%2FPeopleTeam%2DSharedDrive%2FShared%20Documents%2FPeople%20Team%20Shared%20Drive%2FOrganisational%20Development%20%26%20Learning%2FCareer%20Lattice%2FApproved%20Job%20Profiles%2FEngineering%2FEngineering&p=true&ga=1",
                (short) 1);

        String mockedValidationError = "Invalid job role exception";

        // when
        when(jobRoleValidatorMock.isValidJobRole(mockJobRoleRequest)).thenReturn(mockedValidationError);
        // then
        assertThatExceptionOfType(InvalidJobRoleException.class)
                .isThrownBy(() -> jobRoleService.createJobRole(mockJobRoleRequest))
                .withMessageMatching(mockedValidationError);
    }

    @Test
    void createJobRole_When_ThereIsDatabaseError_Expect_FailedToCreateJobRoleExceptionToBeThrown() throws SQLException, FailedToCreateJobRoleException {
        // given
        JobRoleRequest mockedJobRoleRequest = new JobRoleRequest("Front-end Engineer", "As a Front-end Engineer in Kainos, you will have the opportunity to use your expertise in developing high quality user interface solutions which delight our customers and impact the lives of users worldwide.", "https://kainossoftwareltd.sharepoint.com/sites/PeopleTeam-SharedDrive/Shared%20Documents/Forms/AllItems.aspx?id=%2Fsites%2FPeopleTeam%2DSharedDrive%2FShared%20Documents%2FPeople%20Team%20Shared%20Drive%2FOrganisational%20Development%20%26%20Learning%2FCareer%20Lattice%2FApproved%20Job%20Profiles%2FEngineering%2FEngineering%2FJob%20Profile%20%2D%20Front%2DEnd%20Engineer%20%28A%29%2Epdf&parent=%2Fsites%2FPeopleTeam%2DSharedDrive%2FShared%20Documents%2FPeople%20Team%20Shared%20Drive%2FOrganisational%20Development%20%26%20Learning%2FCareer%20Lattice%2FApproved%20Job%20Profiles%2FEngineering%2FEngineering&p=true&ga=1", (short) 1);
        // when
        when(jobRoleDaoMock.createJobRole(mockedJobRoleRequest)).thenThrow(new SQLException());
        // then
        assertThatExceptionOfType(FailedToCreateJobRoleException.class)
                .isThrownBy(() -> jobRoleService.createJobRole(mockedJobRoleRequest))
                .withMessageMatching("Failed to create job role");
    }


    @Test
    void createJobRole_When_JobRoleInputIsValid_Expect_NewJobRoleToBeReturned() throws SQLException, FailedToCreateJobRoleException, InvalidJobRoleException {
        // given
        JobRoleRequest mockedJobRoleRequest = new JobRoleRequest("MockedName", "MockedDescription", "https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/RegExp/test", (short) 1);
        // when
        when(jobRoleDaoMock.createJobRole(mockedJobRoleRequest)).thenReturn(MOCKED_JOB_ROLE);
        // then
        JobRole newJobRole = jobRoleService.createJobRole(mockedJobRoleRequest);
        assertThat(newJobRole).isEqualTo(MOCKED_JOB_ROLE);
    }
}
