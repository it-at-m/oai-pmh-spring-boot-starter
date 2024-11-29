<div align="center">
  <a href="#">
    <img src="./docs/project_logo.png" alt="Logo" width="333" style="display: block; margin: 0 auto; filter: invert(0)">
  </a>
</div>

# oai-pmh-spring-boot-starter

[![Made with love by it@M][made-with-love-shield]][itm-opensource]
![Maven Central](https://img.shields.io/maven-central/v/:groupId/:artifactId?style=for-the-badge)
![Build Status](https://img.shields.io/gitlab/pipeline-status/it-at-m%2Foai-pmh-spring-boot-starter?style=for-the-badge)
![License](https://img.shields.io/github/license/it-at-m/oai-pmh-spring-boot-starter?style=for-the-badge)
![Spring Boot](https://img.shields.io/badge/spring--boot-3.5.5-brightgreen?style=for-the-badge)
![Java](https://img.shields.io/badge/java-21-brightgreen?style=for-the-badge)
![Gitmoji](https://img.shields.io/badge/gitmoji-%20😜%20😍-FFDD67.svg?style=for-the-badge)

OAI-PMH Spring Boot Starter will help you build an [OAI-PMH](https://www.openarchives.org/pmh/) Data provider
with [Spring Boot](https://github.com/spring-projects/spring-boot)

## Features

#### OAI-PMH-Starter

- **Spring Boot Starter**: Easily integrate OAI-PMH capabilities into your Spring Boot applications.
- **API Documentation**: Comprehensive API documentation using Swagger and OpenAPI.
- **Ready-to-Use Endpoints**: Pre-configured OAI-PMH protocol endpoints for immediate use.
- **Detailed Request Validation**: Robust validation mechanisms to ensure the integrity of incoming requests.

#### OAI-PMH-Schema

- **Java Classes with Jakarta XML Annotations**: Provides annotated Java classes for MARC21 and Dublin Core metadata
  formats.
- **Separation of Concerns**: Facilitates the separation of protocol implementation from data processing, making it
  ideal for backend services or data pipelines.

## Set up

TBD

```xml
<!-- OAI-PMH Java classes conforming to the XML Protocol Schema  -->
<dependency>
  <groupId>de.muenchen.oss.oai</groupId>
  <artifactId>oai-pmh-schema</artifactId>
  <version>1.7.1</version>
</dependency>

        <!-- Spring Starter for OAI-PMH Endpoints  -->
<dependency>
<groupId>de.muenchen.oss.oai</groupId>
<artifactId>oai-pmh-spring-boot-starter</artifactId>
<version>1.7.1</version>
</dependency>
```

## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please open an issue with the tag "enhancement", fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Open an issue with the tag "enhancement"
2. Fork the Project
3. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
4. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
5. Push to the Branch (`git push origin feature/AmazingFeature`)
6. Open a Pull Request

More about this in the [CODE_OF_CONDUCT](/CODE_OF_CONDUCT.md) file.


## License

Distributed under the MIT License. See [LICENSE](LICENSE) file for more information.


## Contact

it@M - opensource@muenchen.de

<!-- project shields / links -->
[made-with-love-shield]: https://img.shields.io/badge/made%20with%20%E2%9D%A4%20by-it%40M-yellow?style=for-the-badge
[itm-opensource]: https://opensource.muenchen.de/

[repo-url]: https://github.com/it-at-m/oai-pmh-spring-boot-starter
