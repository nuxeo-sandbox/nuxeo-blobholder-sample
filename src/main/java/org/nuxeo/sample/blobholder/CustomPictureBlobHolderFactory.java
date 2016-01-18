package org.nuxeo.sample.blobholder;

import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.NuxeoPrincipal;
import org.nuxeo.ecm.core.api.blobholder.BlobHolder;
import org.nuxeo.ecm.core.api.blobholder.BlobHolderFactory;
import org.nuxeo.ecm.platform.picture.api.adapters.PictureBlobHolder;


/**
 * Created by Michaël on 12/21/2015.
 */
public class CustomPictureBlobHolderFactory implements BlobHolderFactory {

    @Override
    public BlobHolder getBlobHolder(DocumentModel doc) {
        BlobHolder blobHolder;
        if (doc.hasFacet("Picture")) {
            NuxeoPrincipal principal = (NuxeoPrincipal) doc.getCoreSession().getPrincipal();
            if (!principal.isMemberOf("Creatives")) {
                blobHolder = new PictureBlobHolder(doc, "picture:views/3/content");
            } else {
                blobHolder = new PictureBlobHolder(doc, "file:content");
            }
        } else {
            blobHolder = null;
        }
        return blobHolder;
    }
}
