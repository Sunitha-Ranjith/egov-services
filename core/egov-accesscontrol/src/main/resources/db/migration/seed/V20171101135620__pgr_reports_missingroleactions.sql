insert into eg_roleaction(roleCode,actionid,tenantId)values('RO',(select id from eg_action where name='Pgr Report MetaData'),'default');
insert into eg_roleaction(roleCode,actionid,tenantId)values('RO',(select id from eg_action where name='Pgr Report'),'default');
insert into eg_roleaction(roleCode,actionid,tenantId)values('RO',(select id from eg_action where name='Report Reload'),'default');

insert into eg_roleaction(roleCode,actionid,tenantId)values('GRO',(select id from eg_action where name='Pgr Report MetaData'),'default');
insert into eg_roleaction(roleCode,actionid,tenantId)values('GRO',(select id from eg_action where name='Pgr Report'),'default');
insert into eg_roleaction(roleCode,actionid,tenantId)values('GRO',(select id from eg_action where name='Report Reload'),'default');


insert into eg_roleaction(roleCode,actionid,tenantId)values('GA',(select id from eg_action where name='Pgr Report MetaData'),'default');
insert into eg_roleaction(roleCode,actionid,tenantId)values('GA',(select id from eg_action where name='Pgr Report'),'default');
insert into eg_roleaction(roleCode,actionid,tenantId)values('GA',(select id from eg_action where name='Report Reload'),'default');

insert into eg_roleaction(roleCode,actionid,tenantId)values('GO',(select id from eg_action where name='Pgr Report MetaData'),'default');
insert into eg_roleaction(roleCode,actionid,tenantId)values('GO',(select id from eg_action where name='Pgr Report'),'default');
insert into eg_roleaction(roleCode,actionid,tenantId)values('GO',(select id from eg_action where name='Report Reload'),'default');
