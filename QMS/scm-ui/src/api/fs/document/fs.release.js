import httpService from '@/libs/service'

export default {
    getListByFolder: function (folderId, data) {
        return httpService.get('/fs/doc/release/getList/' + folderId, data);
    },
    download: function (id, version) {
        return httpService.download("/fs/doc/release/download/" + id, { version: version });
    },
    downloadOrigin: function (id, version) {
        return httpService.download("/fs/doc/release/download-origin/" + id, { version: version });
    },
    publish: function (id, version) {
        return httpService.get("/fs/doc/release/publish/" + id, { version: version });
    },
    republish: function (id, version) {
        return httpService.get("/fs/doc/release/re-publish/" + id, { version: version });
    },
    getDocument: function (id, version) {
        return httpService.get("/fs/doc/release/getdocument/" + id, { version: version });
    },
    workflowVerify: function (id, data) {
        return httpService.get("/fs/doc/release/workflow-verify/" + id, data);
    },
    start: function (id, processDefinitionKey) {
        return httpService.get("/fs/doc/release/workflow-start/" + id, { processDefinitionKey: processDefinitionKey });
    },
    verify: function (id, comment) {
        return httpService.get("/fs/doc/release/workflow-verify/{processInstanceId}" + id, { comment: comment });
    },
    remove: function (id, version) {
        return httpService.post("/fs/doc/release/remove/" + id, { version: version });
    }
}