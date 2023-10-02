INSERT INTO UserRoles (name) VALUES ('user'),('administrator');

INSERT INTO Users(email,password,roleId) VALUES ('admin@kainos.com','admin',2);
INSERT INTO Users(email,password,roleId) VALUES ('user@kainos.com','user',1);

INSERT INTO JobRoles(name,description,link) VALUES ('Principal Architect',
'As a Principal Platform Architect (Principal) for Kainos, you’ll be accountable for leading the delivery of cloud platforms and solutions enabling business transformation which
delight our customers and positively impact the lives of users worldwide. As a technologist
you will be inquisitive and will embrace new technology.
You’ll foster and build relationships with senior stakeholders to establish architectural
principles, strategic direction of platform being delivered including a firm understanding of
functional and non-functional needs. 
You will play a leading role in the Kainos Platforms Capability, driving technology direction
and advancement. You will also guide others in the capability to support their career
journeys, you too will be supported on your career journey, enabling you to achieve your
ambitions.
As a technical leader, you will collaborate with colleagues to establish development of
blueprints and standards, foster customer relationships, contribute to account strategies
and actively share your subject matter knowledge, act as a technology ambassador for
Kainos.',
'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FPlatforms%2FJob%20profile%20%2D%20Principal%20Platform%20Architect%20%28Principal%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FPlatforms&p=true&ga=1'
),('Solution Architect',
'As a Platform Solution Architect (Manager) in Kainos, you’ll be responsible for
leading multi-skilled agile teams to design and deliver high quality solutions on public cloud
which delight our customers.  As a technical leader in Kainos, you’ll work with customer
architects and talented colleagues to design, automate, build and support modern
digital service platforms. You’ll estimate effort and advise on the impact of technical
decisions surrounding technology choices.  You’ll lead with the creation of policy and
standards, provide assurance across Kainos delivery projects and share knowledge
with/mentor those around you. You’ll continually be learning about new technologies and
looking to the future for how we adopt them for customer benefit.',
'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FPlatforms%2FJob%20profile%20%2D%20Platform%20Solution%20Architect%20%28Manager%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FPlatforms&p=true&ga=1'
);