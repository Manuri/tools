package org.wso2.carbonstudio.eclipse.platform.core.internal.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.wso2.carbonstudio.eclipse.platform.core.interfaces.IMediaTypeBulkResolver;
import org.wso2.carbonstudio.eclipse.platform.core.interfaces.IMediaTypeFromFileNameResolver;
import org.wso2.carbonstudio.eclipse.platform.core.interfaces.IMediaTypeFromStreamResolver;
import org.wso2.carbonstudio.eclipse.platform.core.interfaces.IMediaTypeResolver;
import org.wso2.carbonstudio.eclipse.platform.core.interfaces.IMediaTypeResolverProvider;
import org.wso2.carbonstudio.eclipse.utils.file.FileUtils;

public class MediaTypeResolverProviderImpl implements
		IMediaTypeResolverProvider {
	private String id;
	private String mediaType;
	private IMediaTypeResolver mediaTypeResolver;
	private int priority = 1;
	private List<String> extensions = new ArrayList<String>();

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaTypeResolver(IMediaTypeResolver mediaTypeResolver) {
		this.mediaTypeResolver = mediaTypeResolver;
	}

	public IMediaTypeResolver getMediaTypeResolver() {
		return mediaTypeResolver;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}

	public boolean isMediaType(File file) {
		try {
			if (getMediaTypeResolver() instanceof IMediaTypeBulkResolver) {
				IMediaTypeBulkResolver resolver = (IMediaTypeBulkResolver) getMediaTypeResolver();
				Object[] allMediaTypeData = resolver.getAllMediaTypeData();
				Object selectedMediaTypeData = null;
				int currentMaximum = -1;
				for (Object mediaTypeData : allMediaTypeData) {
					resolver.setMediaTypeData(mediaTypeData);
					if (resolver.isInputStreamValidateSupported()) {
						try {
							FileInputStream dataStream = new FileInputStream(file);

							boolean isMedia = resolver
									.isMediaType(dataStream);
							if (isMedia
									&& (selectedMediaTypeData == null || currentMaximum < resolver
											.getPriority())) {
								selectedMediaTypeData = mediaTypeData;
								currentMaximum = resolver.getPriority();
							}
							dataStream.close();
						} catch (UnsupportedOperationException e) {
							// inputstream resolver not supported
						} catch (IOException e) {
							// datastream already closed
						}
					}
					if (resolver.isFileNameValidateSupported()) {
						try {
							boolean isMedia = resolver.isMediaType(file
									.getName());
							if (isMedia
									&& (selectedMediaTypeData == null || currentMaximum < resolver
											.getPriority())) {
								selectedMediaTypeData = mediaTypeData;
								currentMaximum = resolver.getPriority();
							}
						} catch (UnsupportedOperationException e) {
							// filename resolver not supported
						}
					}
				}
				if (selectedMediaTypeData != null) {
					resolver.setMediaTypeData(selectedMediaTypeData);
					setMediaType(resolver.getMediaType());
					setExtensions(resolver.getExtensions());
					setPriority(resolver.getPriority());
					return true;
				} else {
					return false;
				}
			} else if (getMediaTypeResolver() instanceof IMediaTypeFromStreamResolver) {
				FileInputStream dataStream = new FileInputStream(file);
				return isMediaType(dataStream);
			} else if (getMediaTypeResolver() instanceof IMediaTypeFromFileNameResolver) {
				return isMediaType(file.toString());
			}
		} catch (FileNotFoundException e) {
			// If the file is not there cannot determine the media type
		}
		return false;
	}

	public boolean isMediaType(URL url) {
		try {
			File tempFile = new File(FileUtils.createTempDirectory(),url.getFile());
			tempFile.getParentFile().mkdirs();
			FileUtils.createFile(tempFile, url.openStream());
			return isMediaType(tempFile);
		} catch (FileNotFoundException e) {
			// never going to happen
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isMediaType(String fileName) {
		if (getMediaTypeResolver() instanceof IMediaTypeBulkResolver) {
			IMediaTypeBulkResolver resolver = (IMediaTypeBulkResolver) getMediaTypeResolver();
			Object[] allMediaTypeData = resolver.getAllMediaTypeData();
			Object selectedMediaTypeData = null;
			int currentMaximum = -1;
			for (Object mediaTypeData : allMediaTypeData) {
				resolver.setMediaTypeData(mediaTypeData);
				try {
					if (resolver.isFileNameValidateSupported()) {
						boolean isMedia = resolver.isMediaType(fileName);
						if (isMedia
								&& (selectedMediaTypeData == null || currentMaximum < resolver
										.getPriority())) {
							selectedMediaTypeData = mediaTypeData;
							currentMaximum = resolver.getPriority();
						}
					}
				} catch (UnsupportedOperationException e) {
					// filename resolver not supported
				}
			}
			if (selectedMediaTypeData != null) {
				resolver.setMediaTypeData(selectedMediaTypeData);
				setMediaType(resolver.getMediaType());
				setExtensions(resolver.getExtensions());
				setPriority(resolver.getPriority());
				return true;
			} else {
				return false;
			}
		} else if (getMediaTypeResolver() instanceof IMediaTypeFromFileNameResolver) {
			return ((IMediaTypeFromFileNameResolver) getMediaTypeResolver())
					.isMediaType(fileName);
		}
		return false;
	}

	public boolean isMediaType(InputStream dataStream) {
		if (getMediaTypeResolver() instanceof IMediaTypeBulkResolver) {
			IMediaTypeBulkResolver resolver = (IMediaTypeBulkResolver) getMediaTypeResolver();
			Object[] allMediaTypeData = resolver.getAllMediaTypeData();
			Object selectedMediaTypeData = null;
			int currentMaximum = -1;
			for (Object mediaTypeData : allMediaTypeData) {
				resolver.setMediaTypeData(mediaTypeData);
				try {
					if (resolver.isInputStreamValidateSupported()) {
						boolean isMedia = resolver.isMediaType(dataStream);
						if (isMedia
								&& (selectedMediaTypeData == null || currentMaximum < resolver
										.getPriority())) {
							selectedMediaTypeData = mediaTypeData;
							currentMaximum = resolver.getPriority();
						}
						dataStream.close();
					}
				} catch (UnsupportedOperationException e) {
					// inputstream resolver not supported
				} catch (IOException e) {
					// datastream already closed
				}
			}
			if (selectedMediaTypeData != null) {
				resolver.setMediaTypeData(selectedMediaTypeData);
				setMediaType(resolver.getMediaType());
				setExtensions(resolver.getExtensions());
				setPriority(resolver.getPriority());
				return true;
			} else {
				return false;
			}
		} else if (getMediaTypeResolver() instanceof IMediaTypeFromStreamResolver) {
			return ((IMediaTypeFromStreamResolver) getMediaTypeResolver())
					.isMediaType(dataStream);
		}
		return false;
	}

	public void setExtensions(List<String> extensions) {
		this.extensions = extensions;
	}

	public List<String> getExtensions() {
		return extensions;
	}

	public void addExtensions(String... extensions) {
		getExtensions().addAll(Arrays.asList(extensions));
	}

	public String getDefaultExtension() {
		return getExtensions().size() > 0 ? getExtensions().get(0) : null;
	}

	public boolean isMultipleProviders() {
		return getMediaTypeResolver() instanceof IMediaTypeBulkResolver;
	}

	public Object[] getMutipleProviderData() {
		IMediaTypeBulkResolver resolver = (IMediaTypeBulkResolver) getMediaTypeResolver();
		return resolver.getAllMediaTypeData();
	}

	public IMediaTypeResolverProvider getProvider(Object mutipleProviderData) {
		IMediaTypeBulkResolver resolver = (IMediaTypeBulkResolver) getMediaTypeResolver();
		resolver.setMediaTypeData(mutipleProviderData);
		setMediaType(resolver.getMediaType());
		setExtensions(resolver.getExtensions());
		setPriority(resolver.getPriority());
		return this;
	}

}
