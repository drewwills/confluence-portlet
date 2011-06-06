/**
 * Implements all the <code>WikiService</code>s using the Confluence API.
 * <p>
 * If the action needs a manager object from Conflunece look at
 * {@link edu.uiuc.cites.portal.wiki.plugin.guice}.
 * <p>
 * All actions have a corresponding servlet in the parent package.
 * <p>
 * This package has the code most likely to break since it recreates what
 * Confluence presents to users and often Confluence does this ignorant of the
 * idea that others may want these results. For each action, if code requires
 * Confluence references, they will be provided in the comments.
 */
@DefaultAnnotation(NonNull.class)
package edu.illinois.my.wiki.plugin.servlet.action;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

