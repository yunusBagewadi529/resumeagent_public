# ResumeAgent
## AI-Driven Resume Optimization and Generation Platform

### Project Synopsis

---

## Executive Summary

**ResumeAgent** is a production-grade, AI-powered web application designed to solve a critical challenge in modern recruitment: the disconnect between qualified candidates and Applicant Tracking Systems (ATS). The platform transforms a single master resume into multiple job-specific, ATS-optimized versions through an intelligent multi-agent AI architecture, ensuring candidates present their qualifications effectively for each opportunity.

Built with enterprise-grade technologies including Java 21, Spring Boot, Next.js, and PostgreSQL, ResumeAgent emphasizes **accuracy**, **user control**, and **cost-efficient AI usage**. The system maintains strict factual consistency while generating professional, editable DOCX resumes that comply with ATS requirements and recruiter preferences.

### Key Differentiators

- **Multi-agent AI architecture** ensuring accuracy and preventing hallucinations
- **Template-driven document generation** separating content from presentation
- **Complete user control** with fully editable DOCX outputs
- **Cost-optimized AI usage** through structured JSON outputs and focused prompts
- **Comprehensive database design** supporting versioning, auditing, and subscription management

---

## 1. Problem Analysis

### 1.1 The ATS Challenge

In contemporary recruitment, Applicant Tracking Systems serve as the first gatekeepers in the hiring process. Studies indicate that **over 75% of resumes are rejected by ATS** before reaching human recruiters. This automated screening creates a significant barrier for qualified candidates whose resumes lack proper optimization, not due to insufficient qualifications, but because of:

- **Missing or mismatched keywords** specific to each job description
- **Non-standard section headings and formatting** that confuse parsing algorithms
- **Generic content** that fails to highlight relevant experience for specific roles
- **Visual formatting elements** (tables, graphics, complex layouts) that hinder text extraction

### 1.2 Current Solution Inadequacies

Existing approaches to this problem present significant limitations:

#### Manual Customization
Tailoring resumes manually for each application is time-consuming and error-prone. Jobseekers often lack the expertise to identify critical keywords or understand ATS parsing requirements, resulting in inconsistent optimization across applications.

#### Traditional Resume Builders
Generic resume builders focus on visual design rather than ATS compatibility. They offer limited customization for specific job descriptions and lack intelligent content optimization, producing one-size-fits-all documents that fail to address individual job requirements.

#### Generic AI Tools
Many AI-powered resume tools suffer from:
- **Hallucination problems**: Fabricating skills, experiences, or achievements not present in the original resume
- **Verbose and unfocused content**: Generating unnecessarily long descriptions that dilute key information
- **Lack of structure**: Failing to maintain consistent JSON or data representation
- **No user control**: Producing final outputs that users cannot effectively edit or customize
- **Privacy concerns**: Unclear data handling and potential misuse of personal information

### 1.3 Core Problems Addressed

ResumeAgent directly addresses the following critical gaps:

1. **Lack of job-specific tailoring** - One resume doesn't fit all positions
2. **Poor ATS compatibility** - Formatting and structure that fails automated screening
3. **Absence of structured representation** - No consistent, machine-readable resume format
4. **Inefficient and costly AI usage** - Wasteful token consumption and unpredictable costs
5. **Limited post-generation control** - Users unable to edit or refine AI outputs effectively

---

## 2. Project Objectives

ResumeAgent is designed to achieve the following primary objectives:

### Core Functional Objectives

- **Generate job-specific resumes** from a single master resume tailored to each job description
- **Ensure ATS-friendly structure** with proper keyword alignment and standard formatting
- **Maintain factual accuracy** and completely avoid hallucinated or fabricated content
- **Provide editable, professional DOCX resumes** that users can customize freely

### Technical Objectives

- **Minimize AI token usage** and operational costs through efficient prompt design
- **Offer a clean, intuitive web-based user experience** with modern UI/UX principles
- **Enforce controlled usage** via a subscription-based quota system
- **Support future extensibility** including multiple templates, versioning, and analytics

---

## 3. System Architecture Overview

ResumeAgent is a full-stack web application consisting of a modern frontend, robust backend, AI-driven processing services, and a document generation pipeline.

### 3.1 High-Level Application Flow

```
User (Web Browser)
    ↓
Frontend (Next.js + TypeScript + Shadcn UI)
    ↓
Spring Boot REST API
    ↓
Resume Processing Pipeline
    ↓
Multi-Agent AI System
    ↓
Structured Resume JSON
    ↓
Template Rendering Engine (DOCX)
    ↓
Final Editable Resume Download
```

The application follows a **backend-first but user-centric design**, ensuring both technical robustness and exceptional usability.

### 3.2 Technology Stack

#### Backend Technologies
- **Java 21 (LTS)** - High-performance runtime with modern concurrency support
- **Spring Boot** - Enterprise-grade framework for REST APIs, dependency injection, and configuration
- **Spring AI** - Abstraction layer for seamless AI model integration
- **Spring Data JPA & Hibernate** - Object-relational mapping and database access
- **PostgreSQL** - Robust relational database for users, resumes, and usage tracking
- **FlywayDB** - Database schema versioning and automated migrations

#### AI Integration
- **OpenRouter** - Unified API gateway supporting multiple OpenAI-compatible models
- **Multi-Agent Architecture** - Specialized agents performing focused, independent tasks
- **Future Local LLM Support** - Designed for offline or low-cost usage scenarios

#### Frontend Technologies
- **Next.js** - React-based framework for server-side rendering and optimal performance
- **TypeScript** - Type safety, improved maintainability, and developer productivity
- **Shadcn UI** - Modern, accessible, and customizable component library
- **Resume Editor Interface** - Interactive editing capabilities
- **Template Selection & Live Preview** - Real-time visualization of resume changes

#### Document Generation
- **FreeMarker (FTL)** - Template engine for dynamic content injection
- **DOCX Templates** - ATS-compliant, professionally formatted resume layouts
- **docx4j / Apache POI** - Programmatic DOCX document generation and manipulation

---

## 4. Core Design Philosophy

### 4.1 Full Application Mindset

ResumeAgent is designed as a **complete product**, not merely an AI experiment or backend service. Equal emphasis is placed on:
- Backend logic and API design
- Frontend user experience
- AI reliability and accuracy
- User control and data ownership

### 4.2 Separation of Concerns

The architecture maintains strict boundaries:

- **AI agents generate content only** - No involvement in formatting or layout decisions
- **Templates control layout and formatting** - Deterministic, consistent visual presentation
- **Users retain full editing control** - Complete ownership of final documents

### 4.3 Deterministic and Reliable Output

- **AI never handles visual formatting** - Preventing inconsistent or unpredictable layouts
- **Resume structure is strictly template-driven** - Ensuring ATS compatibility
- **Outputs remain predictable and professional** - Consistent quality across all generations

### 4.4 Cost-Efficient AI Usage

- **Small, focused prompts** - Minimizing token consumption per agent
- **Structured JSON outputs** - Reducing verbose text generation
- **Minimal token reuse** - Efficient data passing between agents
- **Model selection per task** - Using appropriate models for specific workloads

---

## 5. Multi-Agent AI Pipeline Architecture

ResumeAgent employs a **sequential multi-agent pipeline** where each agent has a single, well-defined responsibility. This approach ensures modularity, accuracy, maintainability, and controlled AI usage while minimizing hallucinations.

### 5.1 Agent Execution Flow

```
Resume Input
    ↓
Resume Parser Agent
    ↓
Job Description Analyzer Agent
    ↓
AI-Based Matching Agent
    ↓
Resume Rewrite Agent
    ↓
ATS Optimization Agent
    ↓
Structured Resume JSON
    ↓
Template Renderer
    ↓
Final DOCX Resume
```

### 5.2 Agent Descriptions

#### Agent 1: Resume Parser Agent

**Purpose**: Extract and normalize structured information from the user's master resume, converting unstructured input into a machine-readable format.

**Responsibilities**:
- Parse resumes provided in text or document form
- Identify and extract key resume sections:
    - Personal information
    - Professional summary
    - Skills (technical, soft, domain-specific)
    - Work experience (roles, responsibilities, achievements)
    - Projects (descriptions, technologies, outcomes)
    - Education (degrees, institutions, dates)
    - Certifications (credentials, issuing organizations)
- Normalize extracted data into a predefined resume JSON schema
- Preserve all factual details without modification or enhancement

**Key Characteristics**:
- Focuses strictly on information extraction, not content rewriting
- Handles diverse resume formats consistently
- Serves as the foundational data layer for all downstream agents

**Output**: Clean, structured resume JSON representing the candidate's complete profile

---

#### Agent 2: Job Description Analyzer Agent

**Purpose**: Analyze the target job description and extract structured hiring requirements and expectations.

**Responsibilities**:
- Identify required and preferred skills
- Extract role responsibilities and experience expectations
- Detect domain-specific and ATS-relevant keywords
- Categorize skills into:
    - **Mandatory** - Essential for the role
    - **Preferred** - Nice-to-have qualifications
    - **Contextual** - Industry or domain-specific terminology

**Key Characteristics**:
- Interprets job intent beyond raw keyword matching
- Understands implicit requirements and expectations
- Converts free-text job descriptions into structured requirement data

**Output**: Structured job-requirements JSON used for matching and optimization

---

#### Agent 3: AI-Based Matching Agent

**Purpose**: Intelligently compare the structured resume data with job requirements and determine relevance, alignment, and gaps using AI-driven semantic reasoning.

**Responsibilities**:
- Perform semantic matching between:
    - Resume skills and job-required skills
    - Work experience and job responsibilities
    - Projects and role expectations
- Identify relationships beyond exact keyword matches:
    - Synonyms and related technologies (e.g., "React" and "React.js")
    - Transferable skills (e.g., "team leadership" applicable to various roles)
    - Domain-adjacent competencies (e.g., "Python" relevant for data science and backend development)
- Assign relevance scores for:
    - Skills alignment (how well skills match requirements)
    - Experience alignment (relevance of past roles)
    - Project relevance (applicability of demonstrated work)
- Classify results into:
    - **Strong matches** - Direct alignment with requirements
    - **Partial matches** - Related but not exact fits
    - **Missing but related competencies** - Gaps that could be addressed

**AI Constraints**:
- Matching is limited strictly to resume-provided data
- No assumption or fabrication of experience
- Related skills identified only with clear contextual overlap
- Confidence scoring is conservative and explainable

**Output**: Structured match analysis report highlighting matched skills, priority emphasis areas, and sections requiring contextual enhancement

---

#### Agent 4: Resume Rewrite Agent

**Purpose**: Rewrite resume content using job-specific language while strictly maintaining factual accuracy and professional tone.

**Responsibilities**:
- Rephrase existing resume content to align with job terminology
- Emphasize matched skills, experiences, and projects identified by the Matching Agent
- Improve clarity, impact, and readability of bullet points and descriptions
- Optimize content for relevance and ATS keyword parsing

**Strict Rules**:
- **No fabrication** of skills, roles, or achievements
- **No addition** of information not present in the master resume
- **Rewriting limited to rephrasing and emphasis only**
- All claims must be verifiable against the original master resume

**Key Characteristics**:
- Controlled AI generation with bounded output
- Operates only on validated structured data
- Produces concise, professional, job-aligned content

**Output**: Rewritten resume content JSON aligned with the target job description

---

#### Agent 5: ATS Optimization Agent

**Purpose**: Ensure the final resume content is fully compatible with Applicant Tracking Systems.

**Responsibilities**:
- Validate and optimize keyword placement for maximum ATS scoring
- Enforce standardized section headings (e.g., "Professional Experience" not "Work History")
- Ensure plain-text compatibility without complex formatting
- Remove elements that hinder ATS parsing:
    - Tables for layout purposes
    - Graphics and images
    - Complex multi-column layouts
    - Non-standard fonts or special characters
- Maintain consistent bullet formatting and logical section order

**Key Characteristics**:
- Focuses exclusively on ATS compliance and structure
- Independent of visual design or template aesthetics
- Ensures high compatibility across major ATS platforms (Greenhouse, Lever, Workday, etc.)

**Output**: Final ATS-optimized structured resume JSON, ready for document rendering

---

### 5.3 Agent Pipeline Summary

| Agent                    | Role                        | AI-Based | Output                      |
|--------------------------|-----------------------------|----------|-----------------------------|
| Resume Parser Agent      | Resume data extraction      | Yes      | Structured resume JSON      |
| Job Description Analyzer | Job requirement analysis    | Yes      | Job requirements JSON       |
| Matching Agent           | Semantic alignment analysis | Yes      | Match analysis report       |
| Resume Rewrite Agent     | Content optimization        | Yes      | Job-aligned resume JSON     |
| ATS Optimization Agent   | ATS compliance validation   | Yes      | Final ATS-ready resume JSON |

---

## 6. Resume Template System

ResumeAgent employs a **template-driven resume rendering system** to ensure consistent, professional, and ATS-compliant resume output. The system is built using DOCX templates integrated with FreeMarker (FTL), where AI-generated content is injected into predefined placeholders without allowing AI to influence layout or formatting.

### 6.1 DOCX + FreeMarker Architecture

- **DOCX files** act as the final resume format
- **FreeMarker (FTL)** defines dynamic placeholders and conditional logic within templates
- **Structured resume JSON** produced by the AI pipeline is mapped to template fields
- **Rendering engine** generates fully formatted, editable DOCX documents
- **AI is strictly limited** to content generation; templates fully control layout and presentation

### 6.2 Why DOCX?

ResumeAgent uses DOCX as the primary output format for several critical reasons:

#### Universally Accepted
DOCX is widely supported across all operating systems, devices, and editors, including Microsoft Word, Google Docs, LibreOffice, and Apple Pages.

#### Fully Editable by Users
Users retain complete control to modify, refine, or customize their resumes after generation without breaking formatting or structure.

#### ATS-Friendly
DOCX files are reliably parsed by most Applicant Tracking Systems, ensuring accurate keyword extraction and section recognition.

#### Recruiter-Preferred
Recruiters commonly request DOCX resumes due to ease of review, commenting, annotation, and internal sharing within hiring teams.

### 6.3 Template Design Principles

#### Standardized Section Structure
Uses conventional, industry-standard headings such as:
- Professional Summary
- Skills
- Professional Experience
- Projects
- Education
- Certifications

This improves ATS parsing accuracy and recruiter familiarity.

#### Plain Text Compatibility
Avoids:
- Tables for layout purposes
- Graphics, icons, or images
- Complex multi-column layouts
- Embedded charts or diagrams

This ensures reliable text extraction by ATS systems.

#### Consistent Formatting
Ensures uniform:
- Fonts (typically Arial or Caliber)
- Font sizes (11-12pt body, larger headings)
- Spacing and margins
- Bullet styles
- Section order and hierarchy

#### Conditional Rendering
Sections are included or excluded dynamically based on available data. For example:
- Certifications section shown only if certifications are present
- Projects section omitted if no projects exist
- Custom sections rendered based on user's master resume structure

### 6.4 Benefits of the Template-Based Approach

- **Guarantees predictable and professional resume layout**
- **Prevents AI-induced formatting inconsistencies**
- **Allows easy introduction of new resume designs** without changing AI logic
- **Simplifies maintenance and future customization**
- **Supports multiple templates** for different roles, industries, or experience levels

### 6.5 Rendering Process

The resume rendering process is designed to be deterministic, reliable, and user-controlled.

#### Step 1: Load DOCX Template
- A predefined DOCX resume template is selected based on user preference
- The template contains embedded FreeMarker (FTL) placeholders representing resume sections
- Template selection is independent of AI processing, ensuring consistent layout control

#### Step 2: Inject Structured Resume JSON
- The finalized, ATS-optimized resume JSON produced by the AI pipeline is mapped to FreeMarker placeholders
- Conditional logic in the template ensures only relevant sections are rendered
- This step guarantees accurate data binding without modifying underlying content

#### Step 3: Generate Final DOCX
- The FreeMarker engine processes the template and injected data
- The document generation layer (docx4j / Apache POI) produces a fully formatted DOCX file
- Formatting remains stable and ATS-compliant across all generated resumes

#### Step 4: User Review and Edit (Optional)
- The generated DOCX file is made available for download
- Users can edit, refine, or personalize the resume using standard document editors
- All edits remain compatible with ATS systems

### 6.6 Key Characteristics of the Rendering Process

- **AI is not involved in layout or formatting decisions**
- **Output remains predictable and consistent**
- **Users retain complete ownership and control** of the final document
- **Supports future enhancements** such as multi-template rendering and live previews

---

## 7. Database Design

ResumeAgent uses **PostgreSQL** as its primary relational database to provide strong consistency, scalability, security, and transactional integrity. The database layer supports user management, resume lifecycle management, AI observability, security workflows, and quota enforcement.

Database schema versioning and controlled evolution are handled using **FlywayDB**, ensuring reliable migrations across development, testing, and production environments.

### 7.1 PostgreSQL Core Responsibilities

- **Store resume versions** - Every generated resume is versioned, allowing rollback, comparison, and safe iteration
- **Save job-targeted resumes and metadata** - Job title, company targeting, and resume status are persisted for traceability
- **Track AI-generated outputs and agent execution** - Each AI agent's execution is logged with token usage and performance metrics
- **Support multi-user scenarios** - Strong relational boundaries ensure secure data isolation between users
- **Enforce subscription limits** - Resume generation quotas are enforced at the database level to prevent abuse

### 7.2 Core Tables Overview

#### 1. users Table

The central identity table for the application.

| Column Name             | Data Type    | Description                             |
|-------------------------|--------------|-----------------------------------------|
| id                      | UUID         | Primary key, uniquely identifies a user |
| full_name               | VARCHAR(150) | User's full name                        |
| email                   | VARCHAR(150) | Unique email address used for login     |
| password_hash           | VARCHAR(255) | Hashed user password                    |
| user_role               | VARCHAR(10)  | User role (USER or ADMIN)               |
| plan                    | VARCHAR(20)  | Subscription plan (FREE or PRO)         |
| resume_generation_limit | INT          | Maximum resumes allowed per plan        |
| resume_generation_used  | INT          | Number of resumes already generated     |
| is_email_active         | BOOLEAN      | Email verification status               |
| created_at              | TIMESTAMP    | Account creation timestamp              |
| updated_at              | TIMESTAMP    | Last account update timestamp           |

**Responsibilities**:
- Stores user credentials and profile data
- Manages user roles and subscription plans
- Enforces resume generation limits for the Free plan
- Tracks email verification state

---

#### 2. master_resumes Table

Stores the canonical resume for each user.

| Column Name | Data Type | Description                      |
|-------------|-----------|----------------------------------|
| id          | UUID      | Primary key for master resume    |
| user_id     | UUID      | Reference to owning user         |
| resume_json | JSONB     | Structured canonical resume data |
| is_active   | BOOLEAN   | Indicates active master resume   |
| created_at  | TIMESTAMP | Creation timestamp               |
| updated_at  | TIMESTAMP | Last update timestamp            |

**Key Characteristics**:
- Contains the full structured resume JSON
- Acts as the single source of truth for AI generation
- Enforces one master resume per user (Phase 1 design)
- Supports future archival without deletion

---

#### 3. resumes Table

Represents a job-targeted resume instance.

| Column Name        | Data Type    | Description                               |
|--------------------|--------------|-------------------------------------------|
| id                 | UUID         | Primary key for resume                    |
| user_id            | UUID         | Reference to owning user                  |
| master_resume_id   | UUID         | Linked master resume                      |
| job_title_targeted | VARCHAR(150) | Target job title                          |
| company_targeted   | VARCHAR(150) | Target company name                       |
| status             | VARCHAR(30)  | Resume status (ACTIVE, ARCHIVED, DELETED) |
| created_at         | TIMESTAMP    | Resume creation timestamp                 |
| updated_at         | TIMESTAMP    | Last update timestamp                     |

**Responsibilities**:
- Links a user to a master resume
- Stores targeting metadata (job title, company)
- Tracks lifecycle status (active, archived, deleted)

---

#### 4. resume_versions Table

Handles full resume versioning.

| Column Name    | Data Type   | Description                               |
|----------------|-------------|-------------------------------------------|
| id             | UUID        | Primary key for resume version            |
| resume_id      | UUID        | Reference to resume                       |
| version_number | INT         | Sequential version number                 |
| source         | VARCHAR(20) | Source of version (AI, USER_EDIT, IMPORT) |
| resume_json    | JSONB       | Full resume snapshot                      |
| created_at     | TIMESTAMP   | Version creation timestamp                |

**Responsibilities**:
- Stores complete JSON snapshots of each resume version
- Supports rollback and comparison
- Tracks the source of changes (AI-generated, user edit, import)
- Ensures immutability and audit safety

---

#### 5. resume_agent_logs Table

Provides AI observability and auditing.

| Column Name       | Data Type    | Description               |
|-------------------|--------------|---------------------------|
| id                | UUID         | Primary key for log entry |
| user_id           | UUID         | Associated user           |
| resume_id         | UUID         | Related resume (nullable) |
| agent_name        | VARCHAR(100) | Name of AI agent          |
| tokens_input      | INT          | Input tokens used         |
| tokens_output     | INT          | Output tokens generated   |
| execution_time_ms | INT          | Agent execution time      |
| status            | VARCHAR(20)  | Execution status          |
| error_message     | TEXT         | Error details if any      |
| created_at        | TIMESTAMP    | Log creation timestamp    |

**Responsibilities**:
- Logs execution of each AI agent
- Tracks token usage and execution time
- Stores error and partial-failure information
- Critical for cost monitoring, debugging, and optimization

---

### 7.3 Security & Authentication Tables

#### 6. email_verification_tokens Table

| Column Name | Data Type    | Description            |
|-------------|--------------|------------------------|
| id          | UUID         | Primary key            |
| user_id     | UUID         | Associated user        |
| token       | VARCHAR(255) | Verification token     |
| expires_at  | TIMESTAMP    | Token expiration time  |
| used        | BOOLEAN      | Whether token was used |
| created_at  | TIMESTAMP    | Token creation time    |
| used_at     | TIMESTAMP    | Token usage time       |

**Purpose**: Manages email verification workflows with expiration and one-time usage

---

#### 7. password_reset_tokens Table

| Column Name | Data Type    | Description          |
|-------------|--------------|----------------------|
| id          | UUID         | Primary key          |
| user_id     | UUID         | Associated user      |
| token       | VARCHAR(255) | Password reset token |
| expires_at  | TIMESTAMP    | Expiration time      |
| used        | BOOLEAN      | Usage status         |
| created_at  | TIMESTAMP    | Creation time        |
| used_at     | TIMESTAMP    | Usage time           |
| ip_address  | VARCHAR(45)  | Request IP address   |
| user_agent  | TEXT         | Client user agent    |

**Purpose**: Supports secure password recovery with IP tracking for security audits

---

#### 8. password_history Table

| Column Name   | Data Type    | Description               |
|---------------|--------------|---------------------------|
| id            | UUID         | Primary key               |
| user_id       | UUID         | Associated user           |
| password_hash | VARCHAR(255) | Old password hash         |
| created_at    | TIMESTAMP    | Password change timestamp |

**Purpose**: Prevents password reuse and strengthens account security policies

---

#### 9. refresh_tokens Table

| Column Name          | Data Type    | Description              |
|----------------------|--------------|--------------------------|
| id                   | UUID         | Primary key              |
| user_id              | UUID         | Associated user          |
| token_hash           | VARCHAR(255) | Hashed refresh token     |
| expires_at           | TIMESTAMP    | Token expiry time        |
| revoked              | BOOLEAN      | Revocation status        |
| revoked_at           | TIMESTAMP    | Revocation time          |
| replaced_by_token_id | UUID         | Token rotation reference |
| ip_address           | VARCHAR(45)  | Session IP               |
| user_agent           | TEXT         | Session user agent       |
| created_at           | TIMESTAMP    | Creation time            |
| last_used_at         | TIMESTAMP    | Last usage time          |

**Purpose**: Implements secure session continuity with token rotation and revocation. Stores only hashed tokens (never plaintext).

---

### 7.4 System Observability

#### 10. system_logs Table

| Column Name       | Data Type   | Description                |
|-------------------|-------------|----------------------------|
| id                | UUID        | Primary key                |
| log_level         | VARCHAR(10) | Log severity level         |
| log_type          | VARCHAR(50) | Type of log event          |
| http_method       | VARCHAR(10) | HTTP method                |
| endpoint          | TEXT        | API endpoint               |
| status_code       | INT         | HTTP response status       |
| user_id           | UUID        | Associated user            |
| user_role         | VARCHAR(10) | Role of user               |
| ip_address        | VARCHAR(45) | Client IP                  |
| user_agent        | TEXT        | Client user agent          |
| request_id        | UUID        | Request correlation ID     |
| trace_id          | UUID        | Distributed trace ID       |
| message           | TEXT        | Log message                |
| metadata          | JSONB       | Additional structured data |
| error_code        | VARCHAR(50) | Application error code     |
| stack_trace       | TEXT        | Error stack trace          |
| execution_time_ms | INT         | Execution time             |
| created_at        | TIMESTAMP   | Log creation time          |

**Purpose**: A centralized logging table for:
- API requests and responses
- Authentication failures
- Security events
- System errors and performance metrics
- Enables audit trails, monitoring, and compliance readiness

---

### 7.5 Database Views for Optimized Queries

#### v_complete_resumes
- Provides a consolidated view of resumes with their latest versions
- Used by document rendering and export pipelines

#### v_user_resume_stats
- Tracks user usage metrics
- Enforces subscription limits
- Supports dashboards and admin tools

### 7.6 Database Design Principles

- **Strong referential integrity** using foreign keys
- **JSONB storage** for flexible, structured resume data
- **Indexing and GIN indexes** for performance on JSON queries
- **Soft-deletion and lifecycle flags** for data safety and recovery
- **Auditability and traceability** across all AI and user actions
- **Security-first token handling** with hashing and expiration

### 7.7 Benefits of This Database Architecture

- Fully supports a multi-user SaaS application
- Enables controlled AI usage with quota enforcement
- Provides complete resume version history
- Ensures transparency and trust in AI operations
- Scales cleanly for future paid plans and analytics

---

## 8. End-to-End Workflow

The complete workflow of ResumeAgent follows a structured, deterministic pipeline ensuring accuracy, ATS compliance, and user control.

### Workflow Steps

1. **User Submission**
    - User submits a master resume and target job description through the web interface

2. **Resume Parsing**
    - Resume Parser Agent extracts and structures all resume data into JSON format

3. **Job Analysis**
    - Job Description Analyzer Agent extracts requirements, skills, and keywords from the job posting

4. **Intelligent Matching**
    - AI-Based Matching Agent performs semantic comparison between resume and job requirements
    - Identifies strong matches, partial matches, and emphasis areas

5. **Content Optimization**
    - Resume Rewrite Agent rephrases content using job-specific terminology
    - Emphasizes relevant skills and experiences while maintaining factual accuracy

6. **ATS Compliance**
    - ATS Optimization Agent ensures keyword placement, standard headings, and format compatibility

7. **Template Rendering**
    - Optimized resume JSON is injected into a DOCX template using FreeMarker
    - Document generation layer produces the final DOCX file

8. **User Download and Editing**
    - User downloads the final resume
    - Can freely edit using Microsoft Word, Google Docs, or any DOCX editor
    - All edits remain ATS-compatible

This workflow ensures that **AI assists the user while final ownership and control remain with the user**.

---

## 9. Subscription and Usage Model

ResumeAgent currently operates under a **Free Subscription Plan** with controlled usage to manage AI costs and prevent abuse.

### Current Plan Details

- **5 AI-based resume generations per month** (strict limit)
- **Usage tracked and enforced** at the backend database level
- **Prevents abuse** and manages operational AI costs
- **Subscription system is extensible** for future paid plans

### Future Expansion

The architecture supports:
- Multiple subscription tiers (Free, Pro, Enterprise)
- User-provided API keys for extended usage
- Pay-per-use or credit-based systems
- Team and organization accounts

---

## 10. Key Advantages

ResumeAgent offers several important advantages over traditional resume builders and generic AI tools:

### Modular and Extensible Architecture
Each system component (AI agents, templates, database, UI) is independently extensible, allowing for easy updates and feature additions without system-wide changes.

### Cost-Efficient AI Usage
- Small, focused prompts minimize token consumption
- Structured JSON inputs and outputs reduce verbosity
- Controlled execution prevents wasteful API calls
- Model selection per task optimizes cost-performance ratio

### ATS-Compliant Outputs
- Resume structure and formatting follow industry best practices
- Standardized section headings improve parsing accuracy
- Plain-text compatibility ensures reliable keyword extraction
- Tested against major ATS platforms (Greenhouse, Lever, Workday, etc.)

### Editable Professional Documents
- Final resumes delivered in universally accepted DOCX format
- Users can modify content, formatting, and structure freely
- No vendor lock-in or proprietary formats
- Compatible with all major document editors

### Transparent and Trustworthy AI
- Multi-agent architecture prevents hallucinations
- All claims verifiable against the original master resume
- Clear separation between AI generation and user data
- Complete audit trail of AI operations

### Model-Agnostic AI Integration
- OpenRouter-based integration allows switching between AI models without code changes
- Future support for local LLMs for privacy-sensitive scenarios
- Flexibility to optimize for cost, speed, or quality based on requirements

---

## 11. Future Enhancements

ResumeAgent is designed with long-term scalability and feature growth in mind. Planned enhancements include:

### Near-Term Enhancements

- **Web-based resume editor** with real-time preview and WYSIWYG editing
- **Multiple resume templates** for different roles, industries, and experience levels
- **Resume version comparison and diff view** to track changes over time
- **Skill gap analysis** showing missing qualifications for target roles
- **Improvement recommendations** based on job market trends

### Medium-Term Enhancements

- **AI-generated cover letter support** tailored to each job application
- **Enhanced user authentication** including OAuth and SSO
- **Advanced account management** with usage analytics and insights
- **Paid subscription plans** with higher generation limits
- **User-provided API keys** for unlimited usage

### Long-Term Vision

- **Comprehensive career-optimization platform** with job matching
- **Interview preparation assistance** based on resume and job description
- **LinkedIn profile optimization** and synchronization
- **Career trajectory analysis** and personalized recommendations
- **Integration with job boards** for seamless application submission

---

## 12. Conclusion

ResumeAgent represents a **production-grade AI resume optimization web application** that combines modern AI capabilities with deterministic software engineering principles. By clearly separating:

- **Intelligence** (AI agents)
- **Structure** (JSON and database design)
- **Presentation** (DOCX templates)

The system ensures **reliability, scalability, and long-term maintainability**.

### Core Achievements

ResumeAgent successfully demonstrates:
- How AI can be integrated **responsibly and transparently** into real-world systems
- That AI can **enhance human productivity** without removing user control
- The importance of **structured architecture** in AI-powered applications
- The value of **cost-efficient design** in sustainable AI deployment

### Project Significance

Overall, ResumeAgent represents a **scalable, extensible, and practical solution** to modern hiring challenges and serves as a strong foundation for:
- Real-world deployment in production environments
- Open-source collaboration and community contributions
- Further academic research in AI-human collaboration
- Professional development and portfolio demonstration

The platform addresses a genuine pain point in the job application process while maintaining high standards for accuracy, user control, and ethical AI usage.

---

## Appendix: Technical Specifications

### System Requirements
- **Backend**: Java 21+, Spring Boot 3.x
- **Database**: PostgreSQL 14+
- **Frontend**: Node.js 18+, Next.js 14+
- **AI**: OpenRouter API access or compatible OpenAI API

### Performance Targets
- Resume generation: < 30 seconds end-to-end
- API response time: < 200ms for non-AI endpoints
- Database query optimization: < 50ms for indexed queries
- Concurrent users: 100+ simultaneous resume generations

### Security Features
- Password hashing: BCrypt with salt
- Token-based authentication: JWT with refresh tokens
- API rate limiting: Configurable per endpoint
- Input validation: Server-side validation for all user inputs
- SQL injection prevention: Parameterized queries via JPA

### Compliance & Standards
- GDPR compliance for user data handling
- ATS compatibility with major systems
- RESTful API design following OpenAPI 3.0 specification
- Semantic versioning for API and schema changes

---

**Document Version**: 1.0  
**Last Updated**: January 2026  
**Status**: Production-Ready Design