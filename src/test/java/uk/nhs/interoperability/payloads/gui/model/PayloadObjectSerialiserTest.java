package uk.nhs.interoperability.payloads.gui.model;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import uk.nhs.interoperability.payloads.DateValue;
import uk.nhs.interoperability.payloads.Payload;
import uk.nhs.interoperability.payloads.templates.AuthorPersonUniversal;
import uk.nhs.interoperability.payloads.templates.ChildPatientUniversal;
import uk.nhs.interoperability.payloads.templates.PersonUniversal;
import uk.nhs.interoperability.payloads.toc_edischarge_draftB.ClinicalDocument;
import uk.nhs.interoperability.payloads.vocabularies.generated.x_BasicConfidentialityKind;

public class PayloadObjectSerialiserTest {
	
	@Test
	public void testSerialisePayload() throws UnsupportedEncodingException {
		
		String expected = "{\"name\":\"ClinicalDocument\",\"packg\":\"uk.nhs.interoperability.payloads.toc_edischarge_draftB\",\"fields\":[{\"name\":\"DocumentId\",\"xpath\":\"x:id/@root\",\"description\":\"A DCE UUID to identify this specific document and version\",\"mandatory\":true,\"addAtStart\":false,\"type\":\"String\",\"maxOccurs\":1,\"isDuplicateXPath\":false,\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"String\"},{\"name\":\"DocumentType\",\"xpath\":\"x:code\",\"description\":\"A SNOMED CT compositional grammar statement that describes both the type of document and the care setting it relates to.\",\"mandatory\":true,\"addAtStart\":false,\"type\":\"CompositionalStatement\",\"maxOccurs\":1,\"isDuplicateXPath\":false,\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"expectedExpressionCode\":\"810301000000103\",\"expectedExpressionDisplayName\":\"Clinical document descriptor (record artefact)\",\"typeEnum\":\"CompositionalStatement\"},{\"name\":\"DocumentTitle\",\"xpath\":\"x:title\",\"description\":\"A string which is rendered as a human readable title\",\"mandatory\":true,\"addAtStart\":false,\"type\":\"String\",\"maxOccurs\":1,\"isDuplicateXPath\":false,\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"String\"},{\"name\":\"EffectiveTime\",\"xpath\":\"x:effectiveTime/@value\",\"description\":\"The creation time of the CDA document\",\"mandatory\":true,\"addAtStart\":false,\"type\":\"HL7Date\",\"maxOccurs\":1,\"isDuplicateXPath\":false,\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"HL7Date\"},{\"vocabValues\":{\"vocabName\":\"x_BasicConfidentialityKind\",\"entries\":{\"V\":\"_Veryrestricted\",\"R\":\"_restricted\",\"N\":\"_normal\"}},\"name\":\"ConfidentialityCode\",\"xpath\":\"x:confidentialityCode\",\"mandatory\":true,\"addAtStart\":false,\"vocabulary\":\"x_BasicConfidentialityKind\",\"type\":\"CodedValue\",\"maxOccurs\":1,\"isDuplicateXPath\":false,\"localCodeOID\":\"2.16.840.1.113883.2.1.3.2.4.17.415\",\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"CodedValue\"},{\"name\":\"DocumentSetId\",\"xpath\":\"x:setId/@root\",\"description\":\"A DCE UUID to identify all documents that are part of a set of documents (i.e. previous versions of this document)\",\"mandatory\":true,\"addAtStart\":false,\"type\":\"String\",\"maxOccurs\":1,\"isDuplicateXPath\":false,\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"String\"},{\"name\":\"DocumentVersionNumber\",\"xpath\":\"x:versionNumber/@value\",\"description\":\"The version number of the document as an integer value\",\"mandatory\":true,\"addAtStart\":false,\"type\":\"String\",\"maxOccurs\":1,\"isDuplicateXPath\":false,\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"String\"},{\"name\":\"Patient\",\"xpath\":\"x:recordTarget/x:patientRole\",\"description\":\"Patient Details\",\"mandatory\":true,\"addAtStart\":false,\"type\":\"PatientUniversalv2\",\"typePackage\":\"uk.nhs.interoperability.payloads.templates.\",\"maxOccurs\":1,\"isDuplicateXPath\":false,\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"Other\"},{\"name\":\"TimeAuthored\",\"xpath\":\"x:author/x:time/@value\",\"description\":\"The time the author originally recorded the End of life information\",\"mandatory\":false,\"addAtStart\":false,\"type\":\"HL7Date\",\"maxOccurs\":1,\"isDuplicateXPath\":false,\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"HL7Date\"},{\"name\":\"Author\",\"xpath\":\"x:author/x:assignedAuthor\",\"description\":\"The author of the End of Life care plan is the person who originally recorded the information on the End of Life system\",\"mandatory\":false,\"addAtStart\":false,\"type\":\"Author\",\"typePackage\":\"uk.nhs.interoperability.payloads.templates.\",\"maxOccurs\":1,\"isDuplicateXPath\":false,\"versionedIdentifierXPath\":\"x:author/x:assignedAuthor/x:templateId/@extension\",\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"Templated\"},{\"name\":\"DataEnterer\",\"xpath\":\"x:dataEnterer/x:assignedEntity\",\"description\":\"A data enterer is the person who entered the information contained in the document\",\"mandatory\":false,\"addAtStart\":false,\"type\":\"PersonUniversal\",\"typePackage\":\"uk.nhs.interoperability.payloads.templates.\",\"maxOccurs\":1,\"isDuplicateXPath\":false,\"versionedIdentifierXPath\":\"x:dataEnterer/x:assignedEntity/x:templateId/@extension\",\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"Templated\"},{\"name\":\"Informant\",\"xpath\":\"x:informant\",\"description\":\"A person who informed the author about information contained in the CDA document\",\"mandatory\":false,\"addAtStart\":false,\"type\":\"DocumentInformant\",\"maxOccurs\":100,\"isDuplicateXPath\":false,\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"Other\"},{\"name\":\"CustodianOrganisation\",\"xpath\":\"x:custodian/x:assignedCustodian\",\"description\":\"The organisation responsible for maintaining the information in the CDA document\",\"mandatory\":true,\"addAtStart\":false,\"type\":\"CustodianOrganizationUniversal\",\"typePackage\":\"uk.nhs.interoperability.payloads.templates.\",\"maxOccurs\":1,\"isDuplicateXPath\":false,\"versionedIdentifierXPath\":\"x:custodian/x:assignedCustodian/x:templateId/@extension\",\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"Templated\"},{\"name\":\"PrimaryRecipients\",\"xpath\":\"x:informationRecipient[@typeCode\u003d\u0027PRCP\u0027]\",\"description\":\"Primary recipients\",\"mandatory\":false,\"addAtStart\":false,\"type\":\"PrimaryRecipient\",\"maxOccurs\":100,\"isDuplicateXPath\":false,\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"Other\"},{\"name\":\"InformationOnlyRecipients\",\"xpath\":\"x:informationRecipient[@typeCode\u003d\u0027TRC\u0027]\",\"description\":\"Recipients who are sent a copy of the CDA document for information only. They are not normally required to carry out any action on receipt of the CDA document.\",\"mandatory\":false,\"addAtStart\":false,\"type\":\"InformationOnlyRecipient\",\"maxOccurs\":100,\"isDuplicateXPath\":false,\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"Other\"},{\"name\":\"TimeAuthenticated\",\"xpath\":\"x:authenticator/x:time/@value\",\"description\":\"When the person authenticated the CDA document\",\"mandatory\":false,\"addAtStart\":false,\"type\":\"HL7Date\",\"maxOccurs\":1,\"isDuplicateXPath\":false,\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"HL7Date\"},{\"name\":\"Authenticator\",\"xpath\":\"x:authenticator/x:assignedEntity\",\"description\":\"This is used where the CDA document needs to be authenticated. This means that the person who authenticated will have overall responsibility for the document not the author. For example a supervisor.\",\"mandatory\":true,\"addAtStart\":false,\"type\":\"PersonUniversal\",\"typePackage\":\"uk.nhs.interoperability.payloads.templates.\",\"maxOccurs\":1,\"isDuplicateXPath\":false,\"versionedIdentifierXPath\":\"x:authenticator/x:assignedEntity/x:templateId/@extension\",\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"Templated\"},{\"name\":\"Participant\",\"xpath\":\"x:participant\",\"description\":\"Participants are people, organisations or devices that are involved in some way with the CDA document.\",\"mandatory\":false,\"addAtStart\":false,\"type\":\"DocumentParticipant\",\"maxOccurs\":100,\"isDuplicateXPath\":false,\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"Other\"},{\"name\":\"DocumentationOf\",\"xpath\":\"x:documentationOf\",\"description\":\"This links the document header to the Service Event being documented in the CDA document\",\"mandatory\":false,\"addAtStart\":false,\"type\":\"DocumentationOf\",\"maxOccurs\":100,\"isDuplicateXPath\":false,\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"Other\"},{\"name\":\"AuthorizingConsent\",\"xpath\":\"x:authorization/x:consent\",\"description\":\"Details of consent/authorisation for the creation/sharing of the document\",\"mandatory\":false,\"addAtStart\":false,\"type\":\"Consent\",\"typePackage\":\"uk.nhs.interoperability.payloads.templates.\",\"maxOccurs\":1,\"isDuplicateXPath\":false,\"versionedIdentifierXPath\":\"x:authorization/x:consent/x:templateId/@extension\",\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"Templated\"},{\"name\":\"EncompassingEncounter\",\"xpath\":\"x:componentOf/x:encompassingEncounter\",\"description\":\"Used to link the document header to the EncompassingEncounter that the CDA document is documenting\",\"mandatory\":false,\"addAtStart\":false,\"type\":\"EncompassingEncounter\",\"typePackage\":\"uk.nhs.interoperability.payloads.templates.\",\"maxOccurs\":1,\"isDuplicateXPath\":false,\"versionedIdentifierXPath\":\"x:componentOf/x:encompassingEncounter/x:templateId/@extension\",\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"Templated\"},{\"name\":\"DocumentSections\",\"xpath\":\"x:component/x:structuredBody/x:component\",\"description\":\"A list of the text sections making up the document, linked with each PRSB heading\",\"mandatory\":false,\"addAtStart\":false,\"type\":\"DocumentSection\",\"maxOccurs\":100,\"isDuplicateXPath\":false,\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"Other\"},{\"name\":\"NonXMLBodyText\",\"xpath\":\"x:component/x:nonXMLBody/x:text\",\"description\":\"The information which makes up the document body when the standard CDA structure (mark-up) is not used.\",\"mandatory\":false,\"addAtStart\":false,\"type\":\"String\",\"maxOccurs\":1,\"isDuplicateXPath\":false,\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"String\"},{\"name\":\"NonXMLBodyMediaType\",\"xpath\":\"x:component/x:nonXMLBody/x:text/@mediaType\",\"description\":\"The mime type of the non-CDA body.\",\"mandatory\":true,\"addAtStart\":false,\"type\":\"String\",\"maxOccurs\":1,\"isDuplicateXPath\":false,\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"String\"},{\"name\":\"NonXMLBodyType\",\"description\":\"Encoding of non-CDA body - must be either TXT or B64. Please use the AttachmentType internal vocab.\",\"mandatory\":false,\"addAtStart\":false,\"vocabulary\":\"AttachmentType\",\"type\":\"String\",\"maxOccurs\":1,\"isDuplicateXPath\":false,\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"String\"},{\"vocabValues\":{\"vocabName\":\"Documenttype\",\"entries\":{\"766781000000107\":\"_NHSContinuingHealthcareChecklistAssessment\",\"958141000000101\":\"_BAAFCinfantreportsent\",\"313051000000108\":\"_MentalHealthandSubstanceMisuseSpecialistServicesModelsofCareReviewSummary\",\"715611000000100\":\"_AssessmentNotificationAcceptResponseUnderSection2oftheCommunityCareDelayedDischargesetcAct2003\",\"715651000000101\":\"_DischargeNotificationDisagreementUnderSection5oftheCommunityCareDelayedDischargesetcAct2003\",\"313061000000106\":\"_MentalHealthandSubstanceMisuseSpecialistServicesUnspecifiedCareReviewSummary\",\"313101000000108\":\"_OverviewAssessment\",\"163351000000104\":\"_DieticianEvent\",\"308375000\":\"_ReportforProcuratorFiscal\",\"766801000000108\":\"_NHSContinuingHealthcareRequestforAssessment\",\"163451000000106\":\"_MentalHealthEvent\",\"801581000000105\":\"_OutpatientClinicAttendanceNoPatientCopyLetter\",\"371529009\":\"_Historyandphysicalreport\",\"184880006\":\"_Applicationtoregisterdisabledreport\",\"371538006\":\"_Advancedirectivereport\",\"163371000000108\":\"_SecondaryCareEvent\",\"491831000000101\":\"_PersonalHealthMonitoringReportRoutineScheduled\",\"308584004\":\"_DLA370report\",\"270358003\":\"_Lifeassurancepreliminaryreport\",\"229059009\":\"_Report\",\"371540001\":\"_Supervisingphysicianreport\",\"163411000000107\":\"_WardAttendance\",\"163571000000101\":\"_DiagnosticImagingEncounter\",\"801551000000104\":\"_OutpatientClinicAttendancePatientLetter\",\"185076008\":\"_BAAFCDadoptchildreport\",\"846951000000108\":\"_GPReferral\",\"163481000000100\":\"_DischargefromMentalHealthServices\",\"305761000000107\":\"_UnsealReport\",\"927081000000105\":\"_Lowergastrointestinaltractendoscopyreport\",\"196981000000101\":\"_GeneralPracticeSummary\",\"715731000000103\":\"_DischargeNotificationUnderSection5oftheCommunityCareDelayedDischargesetcAct2003Document\",\"384101000000107\":\"_TelehealthEvent\",\"304391000000103\":\"_Statementofeducationalneeds\",\"193831000000104\":\"_PathologyRequestEvent\",\"163301000000100\":\"_PhysiotherapistEvent\",\"163511000000106\":\"_PrescriptionCancelRequest\",\"313041000000105\":\"_MentalHealthandSubstanceMisuseSpecialistServicesCareProgrammeApproachReviewSummary\",\"801561000000101\":\"_OutpatientClinicAttendanceMaxillofacialandTertiaryLetter\",\"801541000000102\":\"_OutpatientClinicAttendanceGPLetter\",\"461421000000103\":\"_BAAFB1obstetricreport\",\"313091000000100\":\"_ContactAssessment\",\"444804000\":\"_Multidisciplinarycareconferencereport\",\"918401000000103\":\"_Implanteddevicemaintenancereport\",\"193841000000108\":\"_PathologyReportEvent\",\"859741000000101\":\"_TelehealthReferral\",\"819541000000103\":\"_NHS111Event\",\"422735006\":\"_Summaryclinicaldocument\",\"305751000000109\":\"_SealedEnvelopeEvent\",\"163391000000107\":\"_DischargefromInpatientCare\",\"312341000000104\":\"_UrgentCareSummaryReport\",\"766761000000103\":\"_NHSContinuingHealthcareFastTrackPathwayTool\",\"163311000000103\":\"_OccupationalTherapistEvent\",\"307881004\":\"_DSSRMORM2report\",\"163321000000109\":\"_CommunityMidwifeEvent\",\"715661000000103\":\"_CancellationNotificationUnderSection3oftheCommunityCareDelayedDischargesetcAct2003\",\"163471000000102\":\"_CareProgrammeApproachSummary\",\"846981000000102\":\"_GPReferralRejectResponse\",\"927071000000108\":\"_Uppergastrointestinaltractendoscopyreport\",\"371527006\":\"_Radiologyreport\",\"491821000000103\":\"_PersonalHealthMonitoringReportForInformationOnly\",\"186065003\":\"_Powerofattorneymedicalreport\",\"305771000000100\":\"_SealReport\",\"491811000000109\":\"_PersonalHealthMonitoringReportActionRequired\",\"406550009\":\"_Summaryofneeds\",\"163251000000107\":\"_FocusActOrEventconcept\",\"163421000000101\":\"_SingleAssessmentProcessEvent\",\"271452006\":\"_Solicitorsreport\",\"859751000000103\":\"_TelehealthClinicianResponse\",\"861411000000103\":\"_EndofLifeCareDocument\",\"163581000000104\":\"_DiagnosticImagingReport\",\"371530004\":\"_Clinicalconsultationreport\",\"197391000000101\":\"_NHSDirectEvent\",\"828801000000101\":\"_NHS111FinalAmbulanceRequest\",\"766831000000102\":\"_NHSContinuingHealthcareCancellationNotification\",\"271531001\":\"_BAAFB12adoptionbirthhistory\",\"750021000000100\":\"_IntegratedCareandSupportPlanLocalAuthorityJointFunded\",\"185078009\":\"_BAAFDchild2yrsrepsent\",\"371543004\":\"_Comprehensivehistoryandphysicalreport\",\"766811000000105\":\"_NHSContinuingHealthcareRequestforAssessmentAcceptance\",\"395641000000101\":\"_BritishAssociationforAdoptionandFosteringB2birthhistoryreport\",\"163521000000100\":\"_PrescriptionCancelResponse\",\"828791000000100\":\"_NHS111InterimAmbulanceRequest\",\"371546007\":\"_Educationalvisitreport\",\"163501000000109\":\"_Prescription\",\"861421000000109\":\"_EndofLifeCareCoordinationSummary\",\"313111000000105\":\"_IntegratedCareandSupportPlan\",\"310854009\":\"_Housingreport\",\"184811000\":\"_NHSemployeereportnoexamination\",\"416700005\":\"_Writtenoperativereport\",\"801601000000101\":\"_OutpatientClinicAttendanceAdministrationLetter\",\"270840000\":\"_Disableddriversorangebadge\",\"308621001\":\"_RM10DHSSDMOreport\",\"371524004\":\"_Clinicalreport\",\"373942005\":\"_Dischargesummary\",\"185005006\":\"_ReportfromHealthVisitor\",\"766821000000104\":\"_NHSContinuingHealthcareDecisionSupportTool\",\"846961000000106\":\"_GPReferralDocument\",\"312991000000108\":\"_MentalHealthActAssessmentOutsideSpecialistServices\",\"801611000000104\":\"_OutpatientClinicAttendancePrivatePracticeLetter\",\"909021000000108\":\"_Safeguardingreport\",\"715721000000100\":\"_AssessmentNotificationUnderSection2oftheCommunityCareDelayedDischargesetcAct2003Document\",\"829581000000103\":\"_Lifeinsurancereport\",\"715591000000108\":\"_HospitalDischargeNotificationtoSocialCare\",\"766751000000101\":\"_NHSContinuingHealthcare\",\"371525003\":\"_Clinicalprocedurereport\",\"423876004\":\"_Clinicaldocument\",\"163261000000105\":\"_PrimaryandCommunityCareEvent\",\"866371000000107\":\"_ChildScreeningReport\",\"371526002\":\"_Operativereport\",\"270107007\":\"_Coronerspostmortemreport\",\"308619006\":\"_Warpensionsreport\",\"312711000000101\":\"_AmbulanceServicePatientSummaryReport\",\"134188003\":\"_Statementofspecialeducationalneeds\",\"766791000000109\":\"_NHSContinuingHealthcareChecklistAssessmentAcceptance\",\"285101000000100\":\"_RefusalToSeal\",\"715621000000106\":\"_AssessmentNotificationRejectResponseUnderSection2oftheCommunityCareDelayedDischargesetcAct2003\",\"371545006\":\"_Confirmatoryconsultationreport\",\"270372007\":\"_Disableddriverorangebadgereport\",\"313081000000102\":\"_AssessmentEvent\",\"312421000000103\":\"_NHSDirectSummaryReport\",\"270389004\":\"_Mobilityallowanceclaimrepresentative\",\"399345000\":\"_Adultechocardiographyprocedurereport\",\"193821000000101\":\"_PathologyEvent\",\"184882003\":\"_Disabledregistrationapplicationfullreport\",\"313011000000109\":\"_EntrytoMentalHealthandSubstanceMisuseSpecialistServices\",\"914861000000106\":\"_ETTexercisetolerancetestreport\",\"404187006\":\"_Statementofneeds\",\"163291000000104\":\"_DistrictNurseEvent\",\"308376004\":\"_Policesurgeonpostmortemreport\",\"185014001\":\"_Reporttobenefitsagency\",\"163491000000103\":\"_MedicationManagementEvent\",\"313277003\":\"_Adoptionbirthhistoryreport\",\"909041000000101\":\"_Childsafeguardingreport\",\"717321000000108\":\"_Diagnosticimagingreport\",\"163361000000101\":\"_OutofHoursEvent\",\"270370004\":\"_Drivinglicencefitnessreport\",\"384111000000109\":\"_PersonalHealthMonitoringReport\",\"163431000000104\":\"_SingleAssessmentProcessContactAssessment\",\"196971000000103\":\"_GeneralPracticeInitialSummary\",\"270116006\":\"_Postmortemreport\",\"445042000\":\"_Hospitaloutpatientprogressreport\",\"914851000000108\":\"_AmbulatoryECGelectrocardiographymonitoringreport\",\"184816005\":\"_Localauthorityemployeereport\",\"339561000000108\":\"_WithdrawalNotification\",\"801571000000108\":\"_OutpatientClinicAttendanceResultsLetter\",\"163541000000107\":\"_DispensedMedication\",\"270102001\":\"_ReportforCoroner\",\"320151000000108\":\"_BiographicalInformation\",\"715681000000107\":\"_CancellationofSection5NotificationUnderSection3oftheCommunityCareDelayedDischargesetcAct2003\",\"697978002\":\"_Providerordersforlifesustainingtreatment\",\"184813002\":\"_Governmentdepartmentemployeereportonly\",\"184881005\":\"_Disabledregistrationapplicationminimalreport\",\"163381000000105\":\"_OutpatientClinicAttendance\",\"371544005\":\"_Targetedhistoryandphysicalreport\",\"163551000000105\":\"_PersonallyAdministeredMedication\",\"715631000000108\":\"_DischargeNotificationUnderSection5oftheCommunityCareDelayedDischargesetcAct2003\",\"766771000000105\":\"_NHSContinuingHealthcareFastTrackPathwayToolAcceptance\",\"750001000000109\":\"_IntegratedCareandSupportPlanContinuingHealthCareFunded\",\"715601000000102\":\"_AssessmentNotificationUnderSection2oftheCommunityCareDelayedDischargesetcAct2003\",\"417185000\":\"_Dictatedoperativereport\",\"371539003\":\"_Conferencereport\",\"313071000000104\":\"_AdmissiontoInpatientCare\",\"184823006\":\"_Specialactivitiesmedreport\",\"283281000000100\":\"_HealthSpacesummary\",\"163751000000100\":\"_CareProgrammeApproachsummary\",\"163331000000106\":\"_HealthVisitorEvent\",\"181221000000100\":\"_DiagnosticImagingRequestEventFocusActOrEvent\",\"801591000000107\":\"_OutpatientClinicAttendanceSafeguardedLetter\",\"308575004\":\"_Employmentreport\",\"163441000000108\":\"_SingleAssessmentProcessOverviewAssessment\",\"308585003\":\"_DS1500report\",\"313031000000101\":\"_ExitfromMentalHealthandSubstanceMisuseSpecialistServices\",\"371534008\":\"_Summaryreport\",\"313021000000103\":\"_UpdatetoMentalHealthandSubstanceMisuseSpecialistServices\",\"270390008\":\"_BAAFAdult12adoptionapplicantreport\",\"909031000000105\":\"_Adultsafeguardingreport\",\"163271000000103\":\"_GeneralPractitionerEvent\",\"313141000000106\":\"_MentalHealthandSubstanceMisuseSpecialistServicesEvent\",\"371532007\":\"_Progressreport\",\"308588001\":\"_BAAFCinfantreport\",\"184850001\":\"_Insurancereporttravelcancellation\",\"163531000000103\":\"_ProtocolSupplyMedication\",\"819551000000100\":\"_NHS111Report\",\"184809009\":\"_Employmentreportnoexamination\",\"762391000000107\":\"_Cancermultidisciplinaryteamreport\",\"308589009\":\"_BAAFDchild2yrsreport\",\"185291000000100\":\"_EmergencyDepartmentEvent\",\"185301000000101\":\"_MinorInjuriesDepartmentEvent\",\"444754002\":\"_Hospitalinpatientprogressreport\",\"371535009\":\"_Transfersummaryreport\",\"371536005\":\"_Flowsheetreport\",\"310601008\":\"_ReporttoDrugSafetyResearchUnit\",\"371537001\":\"_Consentreport\",\"801531000000106\":\"_OutpatientClinicAttendanceConsultantReferralLetter\",\"846971000000104\":\"_GPReferralAcceptResponse\",\"285091000000108\":\"_NullificationDocument\",\"768131000000105\":\"_NHSContinuingHealthcareFastTrackPathwayToolRejection\",\"750031000000103\":\"_IntegratedCareandSupportPlanLocalAuthorityOnlyFunded\",\"384121000000103\":\"_OutofToleranceNotification\",\"716371000000105\":\"_Diagnosticimagingsummaryreport\",\"184873009\":\"_Socialsecurityclaimreport\",\"716061000000101\":\"_Diagnosticimagingprocedurereport\",\"163341000000102\":\"_ChiropodistEvent\",\"371541002\":\"_Providercommentreport\",\"163401000000105\":\"_DischargefromDayCaseCare\",\"163461000000109\":\"_EntrytoMentalHealthServices\",\"371531000\":\"_Encounterreport\",\"371542009\":\"_Admissionhistoryandphysicalreport\",\"163561000000108\":\"_DiagnosticImagingEvent\",\"715641000000104\":\"_DischargeNotificationAgreementUnderSection5oftheCommunityCareDelayedDischargesetcAct2003\",\"750011000000106\":\"_IntegratedCareandSupportPlanFundedNursingCare\",\"163281000000101\":\"_PracticeNurseEvent\",\"768141000000101\":\"_NHSContinuingHealthcareChecklistAssessmentRejection\",\"313001000000107\":\"_UpdatetoMentalHealthActAssessmentNonSpecialistServices\",\"768151000000103\":\"_NHSContinuingHealthcareRequestforAssessmentRejection\",\"313121000000104\":\"_SpecialistAssessmentOutcome\",\"185311000000104\":\"_WalkinCentreEvent\"}},\"name\":\"PreviousDocumentType\",\"xpath\":\"x:relatedDocument/x:priorParentDocument/x:code\",\"mandatory\":true,\"addAtStart\":false,\"vocabulary\":\"Documenttype\",\"type\":\"CodedValue\",\"maxOccurs\":1,\"isDuplicateXPath\":false,\"localCodeOID\":\"2.16.840.1.113883.2.1.3.2.4.17.337\",\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"CodedValue\"},{\"name\":\"PreviousDocumentVersionID\",\"xpath\":\"x:relatedDocument/x:priorParentDocument/x:id\",\"description\":\"A DCE UUID that identifies the specific document and version that this document replaces\",\"mandatory\":false,\"addAtStart\":false,\"type\":\"String\",\"maxOccurs\":1,\"isDuplicateXPath\":false,\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"String\"},{\"name\":\"PreviousDocumentVersionSetId\",\"xpath\":\"x:relatedDocument/x:priorParentDocument/x:setId\",\"description\":\"A DCE UUID that identifies the document set of the document that this document replaces (should always match the document set of this document)\",\"mandatory\":false,\"addAtStart\":false,\"type\":\"String\",\"maxOccurs\":1,\"isDuplicateXPath\":false,\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"String\"},{\"name\":\"PreviousDocumentVersionNumber\",\"xpath\":\"x:relatedDocument/x:priorParentDocument/x:versionNumber\",\"description\":\"An integer value that identifies the version number of the document that this document replaces\",\"mandatory\":false,\"addAtStart\":false,\"type\":\"String\",\"maxOccurs\":1,\"isDuplicateXPath\":false,\"suppressCodeSystem\":false,\"suppressDisplayName\":false,\"typeEnum\":\"String\"}],\"payload\":{}}";
		ClinicalDocument doc = new ClinicalDocument();
		String json = serialise(doc);
		System.out.println("Serialised payload: " + json);
		//assertEquals(expected, json);
	}
	
	
	@Test
	public void testSerialisePayloadWithCodedValue() {
		ClinicalDocument doc = new ClinicalDocument();
		doc.setConfidentialityCode(x_BasicConfidentialityKind._Veryrestricted);
		String json = serialise(doc);
		System.out.println("Serialised payload: " + json);
	}
	
	@Test
	public void testRoundTripPayloadWithCodedValue() {
		ClinicalDocument doc = new ClinicalDocument();
		doc.setConfidentialityCode(x_BasicConfidentialityKind._Veryrestricted);
		String json = serialise(doc);
		ClinicalDocument deserialised = (ClinicalDocument)deserialise(json);
		assertEquals(deserialised.getConfidentialityCode().getCode(), "V");
		System.out.println("Round-tripped payload: " + deserialised.toString());
	}
	
	@Test
	public void testRoundTripPayloadWithChildPayload() {
		ClinicalDocument doc = new ClinicalDocument();
		PersonUniversal authenticator = new PersonUniversal();
		authenticator.setOrgName("OrgName");
		doc.setAuthenticator(authenticator);
		String json = serialise(doc);
		ClinicalDocument deserialised = (ClinicalDocument)deserialise(json);
		System.out.println("Round-tripped payload: " + deserialised.toString());
		assertEquals(deserialised.getAuthenticator().getOrgName(), "OrgName");
	}
	
	@Test
	public void testRoundTripPayloadWithTemplatedChildPayload() {
		ClinicalDocument doc = new ClinicalDocument();
		AuthorPersonUniversal author = new AuthorPersonUniversal();
		author.setOrganisationName("AuthorOrg");
		doc.setAuthor(author);
		String json = serialise(doc);
		ClinicalDocument deserialised = (ClinicalDocument)deserialise(json);
		System.out.println("Round-tripped payload: " + deserialised.toString());
		assertEquals(((AuthorPersonUniversal)deserialised.getAuthor()).getOrganisationName(), "AuthorOrg");
	}
	
	@Test
	public void testSerialisePayloadWithHL7Date() {
		ClinicalDocument doc = new ClinicalDocument();
		doc.setEffectiveTime(new DateValue("19700101"));
		String json = serialise(doc);
		System.out.println("Serialised payload: " + json);
	}
	
	@Test
	public void testRoundTripPayloadWithHL7Date() {
		ClinicalDocument doc = new ClinicalDocument();
		doc.setEffectiveTime(new DateValue("19700101"));
		String json = serialise(doc);
		ClinicalDocument deserialised = (ClinicalDocument)deserialise(json);
		System.out.println("Round-tripped payload: " + deserialised.toString());
		assertEquals("19700101", deserialised.getEffectiveTime().asString());
	}
	
	private String serialise(Payload doc) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Payload.class, new PayloadObjectSerialiser());
		Gson gson = gsonBuilder.create();
		System.out.println("Serialising payload: " + doc.toString());
		return gson.toJson(doc, Payload.class);
	}
	
	private Payload deserialise(String json) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Payload.class, new PayloadObjectDeserialiser());
		Gson gson = gsonBuilder.create();
		System.out.println("Deserialising JSON: " + json);
		return gson.fromJson(json, Payload.class);
	}

}