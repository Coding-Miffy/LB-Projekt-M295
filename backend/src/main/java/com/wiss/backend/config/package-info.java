/**
 * Dieses Paket enthält alle zentralen Konfigurationsklassen der Anwendung.
 *
 * <p>
 *     Aktuell beinhaltet es:
 * </p>
 * <ul>
 *     <li>{@link com.wiss.backend.config.SwaggerConfig} – Konfiguration der OpenAPI/Swagger-Dokumentation.</li>
 *     <li>{@link com.wiss.backend.config.WebConfig} – CORS-Konfiguration zur Steuerung von Cross-Origin-Zugriffen.</li>
 * </ul>
 *
 * <p>
 *     Die Klassen sind mit {@code @Configuration} annotiert und werden beim Starten der Spring Boot
 *     Anwendung automatisch erkannt. Änderungen in diesem Paket wirken sich global auf das Verhalten
 *     der REST-API aus.
 * </p>
 *
 * @author Natascha Blumer
 * @version 1.0
 * @since 2025-07-18
 */
package com.wiss.backend.config;