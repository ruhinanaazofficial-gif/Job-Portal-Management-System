package com.jobportal.config;

import com.jobportal.entity.Job;
import com.jobportal.entity.Role;
import com.jobportal.entity.User;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        
        // 1. Create Default Admin if it doesn't exist
        // 1. Create Default Admin if it doesn't exist
        if (userRepository.findByEmail("admin@jobportal.com").isEmpty()) {
            User admin = new User();
            admin.setName("System Admin");
            admin.setEmail("admin@jobportal.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ROLE_ADMIN);
            
            userRepository.save(admin);
            System.out.println("Default Admin Account Created.");
        }

        // 2. Create Default Companies (Employers) and Jobs for the Dashboard Mock Data
        if (jobRepository.count() == 0) {
            System.out.println("No jobs found, initializing mock companies and jobs...");

            // Company 1: TechNova
            User employer1 = userRepository.findByEmail("hr@technova.com").orElseGet(() -> {
                User u = new User();
                u.setName("HR Manager");
                u.setCompanyName("TechNova Web Services");
                u.setEmail("hr@technova.com");
                u.setPassword(passwordEncoder.encode("demo123"));
                u.setRole(Role.ROLE_EMPLOYER);
                u.setLocation("San Francisco, CA");
                return userRepository.save(u);
            });

            Job job1 = new Job();
            job1.setTitle("Senior Cloud Architect");
            job1.setDescription("We are looking for an experienced Cloud Architect to deploy and manage our enterprise AWS infrastructure. You will deal with Kubernetes, Docker, and high availability systems.");
            job1.setCategory("IT");
            job1.setSkillsRequired("AWS, Kubernetes, Docker, Terraform");
            job1.setExperience("5-8 Years");
            job1.setSalary("$130,000 - $160,000");
            job1.setJobType("Full-Time");
            job1.setWorkMode("Remote");
            job1.setLocation("San Francisco, CA");
            job1.setPostedBy(employer1);

            // Company 2: FinSecure Corp
            User employer2 = userRepository.findByEmail("careers@finsecure.com").orElseGet(() -> {
                User u = new User();
                u.setName("Hiring Team");
                u.setCompanyName("FinSecure Banking");
                u.setEmail("careers@finsecure.com");
                u.setPassword(passwordEncoder.encode("demo123"));
                u.setRole(Role.ROLE_EMPLOYER);
                u.setLocation("New York, NY");
                return userRepository.save(u);
            });

            Job job2 = new Job();
            job2.setTitle("Financial Data Analyst");
            job2.setDescription("Join our dynamic banking team to analyze high-frequency trading data. Strong mathematical and python background expected.");
            job2.setCategory("Finance");
            job2.setSkillsRequired("Python, SQL, Tableau, Pandas");
            job2.setExperience("2-4 Years");
            job2.setSalary("$90,000 - $110,000");
            job2.setJobType("Full-Time");
            job2.setWorkMode("Hybrid");
            job2.setLocation("New York, NY");
            job2.setPostedBy(employer2);

            // Company 3: PixelPerfect Designs
            User employer3 = userRepository.findByEmail("recruitment@pixelperfect.com").orElseGet(() -> {
                User u = new User();
                u.setName("Creative Director");
                u.setCompanyName("PixelPerfect Agency");
                u.setEmail("recruitment@pixelperfect.com");
                u.setPassword(passwordEncoder.encode("demo123"));
                u.setRole(Role.ROLE_EMPLOYER);
                u.setLocation("London, UK");
                return userRepository.save(u);
            });

            Job job3 = new Job();
            job3.setTitle("Lead UI/UX Designer");
            job3.setDescription("Design beautiful SaaS products. Must have an excellent eye for typography, modern grid systems, and glassmorphic designs.");
            job3.setCategory("Design");
            job3.setSkillsRequired("Figma, Adobe XD, CSS, Wireframing");
            job3.setExperience("3-5 Years");
            job3.setSalary("$85,000 - $105,000");
            job3.setJobType("Contract");
            job3.setWorkMode("Remote");
            job3.setLocation("London, UK");
            job3.setPostedBy(employer3);

            // Company 4: AeroSpace Dynamics
            User employer4 = userRepository.findByEmail("jobs@aerospace.com").orElseGet(() -> {
                User u = new User();
                u.setName("Engineering Lead");
                u.setCompanyName("AeroSpace Dynamics");
                u.setEmail("jobs@aerospace.com");
                u.setPassword(passwordEncoder.encode("demo123"));
                u.setRole(Role.ROLE_EMPLOYER);
                u.setLocation("Austin, TX");
                return userRepository.save(u);
            });

            Job job4 = new Job();
            job4.setTitle("Robotics Engineer");
            job4.setDescription("Help us build the next generation of autonomous drones. Familiarity with ROS, C++, and hardware integration required.");
            job4.setCategory("Engineering");
            job4.setSkillsRequired("C++, ROS, Hardware, Embedded");
            job4.setExperience("4-6 Years");
            job4.setSalary("$110,000 - $140,000");
            job4.setJobType("Full-Time");
            job4.setWorkMode("On-Site");
            job4.setLocation("Austin, TX");
            job4.setPostedBy(employer4);

            // Additional jobs attached to existing companies to make it populated
            Job job5 = new Job();
            job5.setTitle("Frontend Vue.js Developer");
            job5.setDescription("We use Vue3 and Tailwind to build fast and responsive enterprise dashboards.");
            job5.setCategory("IT");
            job5.setSkillsRequired("Vue.js, JavaScript, Tailwind, HTML/CSS");
            job5.setExperience("1-3 Years");
            job5.setSalary("$75,000 - $95,000");
            job5.setJobType("Full-Time");
            job5.setWorkMode("Hybrid");
            job5.setLocation("San Francisco, CA");
            job5.setPostedBy(employer1); // TechNova

            Job job6 = new Job();
            job6.setTitle("Digital Marketing Specialist");
            job6.setDescription("Manage our Google Ads and SEO strategies to boost product visibility.");
            job6.setCategory("Marketing");
            job6.setSkillsRequired("SEO, Google Ads, Content Strategy");
            job6.setExperience("2-4 Years");
            job6.setSalary("$70,000 - $85,000");
            job6.setJobType("Part-Time");
            job6.setWorkMode("Remote");
            job6.setLocation("London, UK");
            job6.setPostedBy(employer3); // PixelPerfect

            jobRepository.saveAll(Arrays.asList(job1, job2, job3, job4, job5, job6));
            System.out.println("Mock Companies and Jobs successfully injected!");
        }
    }
}
