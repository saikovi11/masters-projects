
/*
    Inputs:

    PhysicianID 
    NationalProviderID 
    PhysicianName 
    Email 
    Gender 
    PrimaryState 
    PrimaryCity 
    PrimaryCountry 

*/

create table Physicians (
    ID                              NUMBER not null primary key,
    PhysicianID                     varchar2(10) not null,
    NationalProviderID              varchar2(15) not null,
    PhysicianName                   varchar2(25) not null,
    Email                           varchar2(20) not null,
    Gender                          varchar2(10),
    PrimaryState                    varchar2(2),
    PrimaryCity                     varchar2(20),
    PrimaryCountry                  varchar2(20) not null
)
;


alter table Physicians add constraint physicians_physicianid_uq unique (PhysicianID);
alter table Physicians add constraint physicians_nationalprovider_uq unique (NationalProviderID);
alter table Physicians add constraint physicians_email_uq unique (Email);



/*
    Inputs:
    
    EncounterNumber        
    MedicalRecordNumber    
    Dischargedate          
    AdmissionDate          
    Gender                 
    PatienName             
    PatientPhoneNumber     
    PatientAddress         

*/

CREATE TABLE Discharges(

    ID                              NUMBER not null primary key,
    EncounterNumber                 numeric(25) not null,
    MedicalRecordNumber             numeric(25) not null,
    Dischargedate                   date not null,
    AdmissionDate                   date not null, 
    Gender                          varchar(10) not null,
    PatienName                      varchar(30) not null,
    PatientPhoneNumber              varchar(30) not null,
    PatientAddress                  varchar(255) not null
);



/*
    Inputs:

    ItemCode
    Desciption
*/


alter table Discharges add constraint encounter_uq unique (EncounterNumber);

create table MEDICALITEMS (
    ID                            NUMBER not null primary key,
    MEDICALITEMCODE               varchar2(5) not null,
    MEDICALITEMDESCRIPTION        VARCHAR2(255) not null 
)
;

alter table MEDICALITEMS add constraint medicalItems_Code_uq unique (MEDICALITEMCODE);
alter table MEDICALITEMS add constraint medicalItems_Description_uq unique (MEDICALITEMDESCRIPTION);


/*
    Inputs:
    
    DIAGNOSESCODE
    DIAGNOSESDESCRIPTION
    DIAGNOSESTYPE
*/



create table DIAGNOSES (
    ID                            NUMBER not null primary key,
    DIAGNOSESCODE                 VARCHAR2(10) not null,
    DIAGNOSESDESCRIPTION          VARCHAR2(255) not null,
    DIAGNOSESTYPE                 VARCHAR2(2) not null
)
;

alter table DIAGNOSES add constraint diagnoses_Code_uq unique (DIAGNOSESCODE);
alter table DIAGNOSES add constraint diagnoses_Description_uq unique (DIAGNOSESDESCRIPTION);

/*
    Inputs:
    
    HOSPITALNAME
    HOSPITALID
    LOCATION
    ISTEACHING
*/



create table HOSPITALS (
    ID                            NUMBER not null primary key,
    HOSPITALNAME                  VARCHAR2(30) not null,
    HOSPITALID                    NUMBER not null,
    ISTEACHING                    CHAR(1) not null
)
;

alter table HOSPITALS add constraint HospitalName_uq unique (HOSPITALNAME);
alter table HOSPITALS add constraint HospitalID_uq unique (HOSPITALID);



/*
    Inputs:
    
    PROCEDURESCODE
    PROCEDURESDESCRIPTION
    PROCEDURESTYPE
*/

create table PROCEDURES (
    ID                            NUMBER not null primary key,
    PROCEDURESCODE                VARCHAR2(10) not null,
    PROCEDURESDESCRIPTION         VARCHAR2(255) not null,
    PROCEDURESTYPE                VARCHAR2(2) not null
)
;

alter table PROCEDURES add constraint Procedure_code_uq unique (PROCEDURESCODE);
alter table PROCEDURES add constraint Procedure_Description_uq unique (PROCEDURESDESCRIPTION);


/*
    Inputs:
    
    SPECIALTYNAME
    SPECIALTYCODE
*/



create table SPECIALTIES (
    ID                            NUMBER not null primary key,
    SPECIALTYNAME                 VARCHAR2(255),
    SPECIALTYCODE                 VARCHAR2(4000)
)
;

alter table SPECIALTIES add constraint Specialty_name_uq unique (SPECIALTYNAME);
alter table SPECIALTIES add constraint Specialty_code_uq unique (SPECIALTYCODE);


/*
    Inputs:
    
    DISCHARGE
    MEDICALITEM
    CHARGE
    QUANTITY
*/

create table CHARGES (
    ID                            NUMBER not null primary key,
    DISCHARGE                     NUMBER not null,
    MEDICALITEM                   NUMBER not null,
    CHARGE                        numeric(15) not null,
    QUANTITY                      int default 1
)
;

alter table CHARGES add constraint fk_Discharge_Charges foreign key (DISCHARGE) references Discharges (ID);
alter table CHARGES add constraint fk_MedicalItem_Charges foreign key (MEDICALITEM) references MEDICALITEMS (ID);
alter table CHARGES add constraint Discharge_Charge_uq unique (DISCHARGE, MEDICALITEM);


/*
    Inputs:
    
    DISCHARGE
    DIAGNOSED_PHYSICIAN
    DIAGNOSES
    PRESENTONADMISSION
    SEQUENCE
*/


create table DIAGNOSESBRIDGE (
    ID                            NUMBER not null primary key,
    DISCHARGE                     NUMBER not null,
    DIAGNOSED_PHYSICIAN           NUMBER not null,
    DIAGNOSES                     NUMBER not null,
    PRESENTONADMISSION            char(1) not null,
    SEQUENCE                      INT not null
)
;

alter table DIAGNOSESBRIDGE add constraint fk_Diagnoses_Discharge foreign key (DISCHARGE) references Discharges (ID);
alter table DIAGNOSESBRIDGE add constraint fk_Diagnoses_Physician foreign key (DIAGNOSED_PHYSICIAN) references Physicians (ID);
alter table DIAGNOSESBRIDGE add constraint Diagnoses_Discharge_uq unique (DISCHARGE, SEQUENCE);



/*

Hospital
PHYSICIAN
*/

create table HOSPITALBRIDGE (
    ID                            NUMBER not null primary key,
    HOSPITAL                      NUMBER not null,
    PHYSICIAN                     NUMBER not null
)
;

alter table HOSPITALBRIDGE add constraint fk_Hospital_Physician foreign key (HOSPITAL) references Hospitals (ID);
alter table HOSPITALBRIDGE add constraint fk_Hospital_HospitalID foreign key (PHYSICIAN) references Physicians (ID);
alter table HOSPITALBRIDGE add constraint Physician_Hospital_uq unique (Physician, Hospital);

/*
    DISCHARGE                     
    PROCEDURE                     
    PROCEDURE_PHYSICIAN           
    PROCEDUREDATE                 
    SEQUENCE                      
*/

create table PROCEDURESBRIDGE (
    ID                            NUMBER not null primary key,
    DISCHARGE                     NUMBER not null,
    PROCEDURE                     NUMBER not null,
    PROCEDURE_PHYSICIAN           NUMBER not null,
    PROCEDUREDATE                 DATE,
    SEQUENCE                      INT not null
)
;



alter table PROCEDURESBRIDGE add constraint fk_Procedure_Physician foreign key (PROCEDURE_PHYSICIAN) references Physicians (ID);
alter table PROCEDURESBRIDGE add constraint fk_Procedure_Discharge foreign key (DISCHARGE) references Discharges (ID);
alter table PROCEDURESBRIDGE add constraint fk_Procedure_Procedure foreign key (PROCEDURE) references Procedures (ID);
alter table PROCEDURESBRIDGE add constraint Discharge_Procedures_uq unique (DISCHARGE, SEQUENCE);

/*
    SPECIALTY                     
    PHYSICIAN                     
    ISPRIMARYSPECIALTY            
*/

create table SPECIALTYBRIDGE (
    ID                            NUMBER not null primary key,
    SPECIALTY                     NUMBER not null,
    PHYSICIAN                     NUMBER not null,
    ISPRIMARYSPECIALTY            char(1) not null
)
;

alter table SPECIALTYBRIDGE add constraint fk_Specialty_SpecialtyID foreign key (SPECIALTY) references Specialties (ID);
alter table SPECIALTYBRIDGE add constraint fk_Specialty_Physician foreign key (PHYSICIAN) references Physicians (ID);
alter table SPECIALTYBRIDGE add constraint Specialty_Physician_uq unique (SPECIALTY, PHYSICIAN);

create or replace trigger CHARGES_BIU
    before insert or update 
    on CHARGES
    for each row
begin
    if :new.id is null then
        :new.id := to_number(sys_guid(), 'XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX');
    end if;
end;
/

create or replace trigger DIAGNOSES_BIU
    before insert or update 
    on DIAGNOSES
    for each row
begin
    if :new.id is null then
        :new.id := to_number(sys_guid(), 'XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX');
    end if;
end;
/

create or replace trigger DIAGNOSESBRIDGE_BIU
    before insert or update 
    on DIAGNOSESBRIDGE
    for each row
begin
    if :new.id is null then
        :new.id := to_number(sys_guid(), 'XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX');
    end if;
end;
/

create or replace trigger DISCHARGEPHYSICIANBRIDGE_BIU
    before insert or update 
    on DISCHARGEPHYSICIANBRIDGE
    for each row
begin
    if :new.id is null then
        :new.id := to_number(sys_guid(), 'XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX');
    end if;
end;
/

create or replace trigger HOSPITALBRIDGE_BIU
    before insert or update 
    on HOSPITALBRIDGE
    for each row
begin
    if :new.id is null then
        :new.id := to_number(sys_guid(), 'XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX');
    end if;
end;
/

create or replace trigger HOSPITALS_BIU
    before insert or update 
    on HOSPITALS
    for each row
begin
    if :new.id is null then
        :new.id := to_number(sys_guid(), 'XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX');
    end if;
end;
/

create or replace trigger MEDICALITEMS_BIU
    before insert or update 
    on MEDICALITEMS
    for each row
begin
    if :new.id is null then
        :new.id := to_number(sys_guid(), 'XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX');
    end if;
end;
/

create or replace trigger PROCEDURES_BIU
    before insert or update 
    on PROCEDURES
    for each row
begin
    if :new.id is null then
        :new.id := to_number(sys_guid(), 'XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX');
    end if;
end;
/

create or replace trigger PROCEDURESBRIDGE_BIU
    before insert or update 
    on PROCEDURESBRIDGE
    for each row
begin
    if :new.id is null then
        :new.id := to_number(sys_guid(), 'XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX');
    end if;
end;
/

create or replace trigger SPECIALTIES_BIU
    before insert or update 
    on SPECIALTIES
    for each row
begin
    if :new.id is null then
        :new.id := to_number(sys_guid(), 'XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX');
    end if;
end;
/

create or replace trigger SPECIALTYBRIDGE_BIU
    before insert or update 
    on SPECIALTYBRIDGE
    for each row
begin
    if :new.id is null then
        :new.id := to_number(sys_guid(), 'XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX');
    end if;
end;
/



CREATE SEQUENCE sq_Physicians
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  CACHE 20;
  
  
  CREATE SEQUENCE sq_Discharges
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  CACHE 20;
  
  CREATE SEQUENCE sq_MEDICALITEMS
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  CACHE 20;
  
  CREATE SEQUENCE sq_DIAGNOSES
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  CACHE 20;
  
  CREATE SEQUENCE sq_HOSPITALS
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  CACHE 20;
  
  CREATE SEQUENCE sq_PROCEDURES
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  CACHE 20;
  
  CREATE SEQUENCE sq_SPECIALTIES
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  CACHE 20;
  
  CREATE SEQUENCE sq_CHARGES
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  CACHE 20;
  
  CREATE SEQUENCE sq_DIAGNOSESBRIDGE
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  CACHE 20;
  
  
  CREATE SEQUENCE sq_HOSPITALBRIDGE
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  CACHE 20;
  
  CREATE SEQUENCE sq_PROCEDURESBRIDGE
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  CACHE 20;
  
  
  CREATE SEQUENCE sq_SPECIALTYBRIDGE
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  CACHE 20;
  
  
  
  
  