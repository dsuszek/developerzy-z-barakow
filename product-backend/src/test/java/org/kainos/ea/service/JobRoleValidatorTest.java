package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.model.JobRoleRequest;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JobRoleValidatorTest {
    @Mock
    JobRoleRequest jobRole;

    JobRoleValidator jobRoleValidator = new JobRoleValidator();

    @Test
    void When_JobRoleNameLengthMoreThan50_Expect_MessageReturned() {
        when(jobRole.getName()).thenReturn("Principal ArchitectPrincipal ArchitectPrincipal ArchitectPrincipal ArchitectPrincipal ArchitectPrincipal ArchitectPrincipal ArchitectPrincipal ArchitectPrincipal ArchitectPrincipal Architect");
        assertThat(jobRoleValidator.isValidJobRole(jobRole)).isEqualTo("Length of name of job role greater than 50 characters");
    }

    @Test
    void When_JobRoleDescriptionLengthMoreThan3000_Expect_MessageReturned() {
        when(jobRole.getName()).thenReturn("Principal Architect");
        when(jobRole.getDescription()).thenReturn("CAPABILITY GROUP / JOB FAMILY GROUP: Engineering CAPABILITY / JOB FAMILY: Engineering Strategy and Planning JOB PROFILE TITLE: Technology Leader\n" +
                "MANAGEMENT LEVEL: Leader\n" +
                "JOB PROFILE DESCRIPTION\n" +
                "A technology leader is key strategic role within the business making executive technology decisions on behalf of the business, based upon the sector and practices’ strategic direction and goals.\n" +
                "The core responsibilities of a technology leader in Kainos include setting a Technology direction, a technical advisor to the business and C-level clients, maintaining a commercial edge over other technology services providers, developing and nurturing technical talent across the organisation and representing Kainos as a technology evangelist.\n" +
                "Key responsibilities (scoped to area of responsibility and in priority order) are:\n" +
                "People Development\n" +
                "• Identifying, mentoring and coaching talent within the technical capabilities, supporting a culture of wellbeing and inclusion\n" +
                "• Developing future technology talent aligned with the needs of the business in terms of strategic development and succession planning\n" +
                "• Creating environments for technical talent to thrive and achieve ambitious goals Strategy\n" +
                "• Conducting strategic analysis and make recommendations that could influence the business strategy over medium- and long-term horizons\n" +
                "• Work with Innovation and business development teams to qualify and evidence strategic analysis\n" +
                "• Managing the technology budget and making investments aligned to the business strategy\n" +
                "• Review, question, and support development of Sector/Practice technology strategy Business Development\n" +
                "• Uses cross cutting Sector/ Practice insights to identify business development opportunities where existing or future technologies can assist in solving existing or future business or client’s problems.\n" +
                "• Inspiring potential and existing clients and employees within Kainos markets and driving change where necessary\n" +
                "• Engaging with senior clients as a senior technical adviser. Establishing and developing trusted relationships that position Kainos as their technology partner of choice where strategic customer engagement is required\n" +
                " © KAINOS 2023 PAGE: 1 OF 3\n" +
                "  Communication\n" +
                "• Communicating complicated technical goals to non-technical people\n" +
                "• Engendering enthusiasm about the possibilities new technologies can offer\n" +
                "• Project the Kainos technology voice external to the organisation and represent Kainos in\n" +
                "the broader technology ecosystem\n" +
                "Partner Development\n" +
                "• Ensuring appropriate engagement and relationships across the Kainos technology community are in place with strategic partners\n" +
                "• Identifying and developing future go to market strategies with strategic partners\n" +
                "• Developing peer relationships with key strategic partners\n" +
                "• Qualify and introduce new partners to our business and Sectors/ Practices\n" +
                "• Maximise the value we achieve across the technology team from our partnership\n" +
                "relationships\n" +
                "Innovation\n" +
                "• Supports alignments across your peers and capability in researching and qualifying relevant technology shifts\n" +
                "• Identifying, recommend and drive strategic technology investments (skills, capability, partnerships etc.)\n" +
                "• Researching and qualifying current and future technologies Infosec\n" +
                "• Accountable for delivery focused Infosec across the organisation Delivery and Assurance\n" +
                "• Accountable for Bid and Delivery Assurance across the organisation\n" +
                "• Supporting Sector/ Practice teams to plan for delivery ahead\n" +
                "• Strategic delivery engagement and exceptional escalations\n" +
                "MINIMUM ESSENTIAL REQUIREMENTS:\n" +
                "• Has experience of setting a technology focused vision and directing change across a spectrum of disciplines, balancing a focus on people, customer impact and commercial implications.\n" +
                "• Embeds a continuous improvement approach to direction of process, people and use of technology.\n" +
                "• Proven experience being accountable for different sizes and shapes of technology delivery challenges, e.g. services project, multi-team programme, packaged product. Building relationships and influencing at a c-suite level.\n" +
                "• Has applied innovative/ creative thinking, to new offerings and accounts to develop business; applying strong commercial awareness.\n" +
                "• Able to prioritise their time across multiple major projects particularly when working to deadlines.\n" +
                " © KAINOS 2023 PAGE: 2 OF 3\n" +
                "\n" +
                "  • We are passionate about developing people – a demonstrated ability in managing, coaching, and developing members of your team and wider community.\n" +
                "WHO YOU ARE:\n" +
                "Our vision is to enable outstanding people to create digital solutions that have a positive impact on people’s lives. Our values aren't abstract; they are the behaviours we expect from each other every day and underpin everything that we do. We expect everyone to display our values by being determined in how obstacles are overcome; honest when dealing with others; respectful of how you treat others; creative to find solutions to complex problems and cooperative by sharing information, knowledge and experience.\n" +
                "These values, applied collectively, help to produce an outstanding Kainos person, team and culture.\n" +
                "ABOUT US\n" +
                "At Kainos we use technology to solve real problems for our customers, overcome big challenges for businesses, and make people’s lives easier.\n" +
                "We build strong relationships with our customers and go beyond to change the way they work today and the impact they have tomorrow.\n" +
                "Our two specialist practices, Digital Services and Workday, work globally for clients across healthcare, commercial and the public sector to make the world a little bit better, day by day.\n" +
                "For more information, see kainos.com.");
        assertThat(jobRoleValidator.isValidJobRole(jobRole)).isEqualTo("Length of description of job role greater than 3000 characters");
    }

    @Test
    void When_JobRoleLinkLengthMoreThan1000_Expect_MessageReturned() {
        when(jobRole.getName()).thenReturn("Principal Architect");
        when(jobRole.getDescription()).thenReturn("This is an exemplary description of Principal Architect position.");
        when(jobRole.getLink()).thenReturn("https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20profile%20%2D%20Apprentice%20Software%20Engineer%20%28Apprentice%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20profile%20%2D%20Apprentice%20Software%20Engineer%20%28Apprentice%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20profile%2ems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20profile%2ems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20profile%2");
        assertThat(jobRoleValidator.isValidJobRole(jobRole)).isEqualTo("Length of link to job role greater than 1000 characters");
    }

    @Test
    void When_JobRoleLinkInvalid_Expect_MessageReturned() {
        when(jobRole.getName()).thenReturn("Principal Architect");
        when(jobRole.getDescription()).thenReturn("This is an exemplary description of Principal Architect position.");
        when(jobRole.getLink()).thenReturn("int.com/people/Job%20Specifications/Fo2FJob%20profile%2");
        assertThat(jobRoleValidator.isValidJobRole(jobRole)).isEqualTo("Link does not meet all the criteria");
    }
}
